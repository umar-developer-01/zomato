package kane.zomato.security;

import kane.zomato.dto.LoginDto;
import kane.zomato.dto.LoginResponseDto;
import kane.zomato.dto.UserDto;
import kane.zomato.exception.InvalidCredentialException;
import kane.zomato.respository.UserRepository;
import org.modelmapper.ModelMapper;
import kane.zomato.entity.User;
import kane.zomato.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import kane.zomato.dto.SignupDto;

import java.util.Set;

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

}
