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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;

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
                User user = userService.getUserById(userId);
                // check if the user should be allowed

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

//JWTAuthFilter is a Spring Security filter that:
//
//👉 Runs once for every HTTP request
//👉 Reads the JWT from the Authorization header
//👉 Validates it
//👉 Sets the authenticated user into SecurityContext
//After that, Spring Security treats the request as authenticated.

//Client Request
//   ↓
//JWTAuthFilter   ← (your code runs here)
//        ↓
//SecurityContext (user is set here)
//   ↓
//Controller / API



//Because:
//
//Multiple filters can run in the chain
//
//Another filter might have already authenticated the request
//
//Or your filter might be accidentally registered twice
//
//Or async / forward / internal dispatch occurs
//
//So Spring checks:
//
//        “Has someone already authenticated this request?”
//
//If YES → don’t override
//If NO → authenticate now