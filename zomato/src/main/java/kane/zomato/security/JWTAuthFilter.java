package kane.zomato.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kane.zomato.dto.CustomUserDetails;
import kane.zomato.dto.UserDto;
import kane.zomato.entity.User;
import kane.zomato.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;
    private final ModelMapper modelMapper;



    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException {

        try {
            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
                //Public APIs (login, signup) don’t send JWT
                //Requests without JWT should continue normally
                //➡️ Filter exits early
                filterChain.doFilter(request, response);
                return;
            }

            String token = requestTokenHeader.split("Bearer ")[1];
            Long userId = jwtService.getUserIdFromToken(token);




            // Prevents re-authentication
            //Improves performance
            //Avoids overwriting existing auth
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDto user1 = userService.getUserById(userId);

                User user = modelMapper.map(user1, User.class);


                CustomUserDetails userDetails = new CustomUserDetails(user);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        } catch (JwtException ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
            // HandlerExceptionResolver is used in filters to delegate exception handling to Spring’s global exception mechanism,
            // because controller advice does not catch exceptions thrown before the controller layer.
        } catch (IOException ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }
    }
}



