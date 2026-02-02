package kane.zomato.security;

import kane.zomato.dto.*;
import kane.zomato.exception.InvalidCredentialException;
import kane.zomato.respository.UserRepository;
import org.modelmapper.ModelMapper;
import lombok.extern.slf4j.Slf4j;
import kane.zomato.entity.User;
import kane.zomato.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public UserDto signUp(SignupDto signUpRequestDto) {
        User user = userRepository.findByEmail(signUpRequestDto.getEmail()).orElse(null);

        if (user != null) {
            throw new RuntimeException("User is already present with same email id");
        }

        User newUser = modelMapper.map(signUpRequestDto, User.class);
        newUser.setRoles(Set.of(Role.USER));
        newUser.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        newUser = userRepository.save(newUser);

        return modelMapper.map(newUser, UserDto.class);

    }


    public LoginResponseDto login(LoginDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new InvalidCredentialException("Invalid Credentials"));

        if (!passwordEncoder.matches(
                loginRequestDto.getPassword(),
                user.getPassword()
        )) {
            throw new InvalidCredentialException("Invalid Credentials");
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return LoginResponseDto.builder()
                .user(modelMapper.map(user, UserDto.class))
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }


    public RefreshResponseDto refreshToken(String authHeader){
        String refreshToken = authHeader.substring(7);
        log.info("This is the refreshToken={}", refreshToken);

        JwtDto jwtDto = jwtService.isTokenValid(refreshToken);

        if (!jwtDto.isValid()) {
            throw new RuntimeException(jwtDto.getMessage());
        }

        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newAccessToken = jwtService.generateAccessToken(user);
        log.info("New access token generated for userId={}", userId);

        return RefreshResponseDto.builder()
                .accessToken(newAccessToken)
                .build();
    }
}
