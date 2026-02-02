package kane.zomato.controller;
import jakarta.validation.Valid;
import kane.zomato.dto.*;


import lombok.extern.slf4j.Slf4j;
import kane.zomato.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Auth {


    private final AuthService authService;


    @PostMapping("/signup")
    @Operation(summary = "Create a new account", tags = {"Auth"})
    public ResponseEntity<UserDto> signup(@Valid @RequestBody SignupDto signUpRequestDto) {
        log.info("Signup request received: {}", signUpRequestDto);
        return new ResponseEntity<>(authService.signUp(signUpRequestDto), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    @Operation(summary = "Login to a account", tags = {"Auth"})
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginRequestDto) {
        log.info("Login request received: {}", loginRequestDto);
        return new ResponseEntity<>(authService.login(loginRequestDto), HttpStatus.CREATED);
    }


    @GetMapping("/refresh")
    @Operation(summary = "Refresh the auth token", tags = {"Auth"})
    public ResponseEntity<RefreshResponseDto> refreshToken(@RequestHeader("Authorization") String authHeader) {
        log.info("Refresh request received: {}", authHeader);
        return new ResponseEntity<>(authService.refreshToken(authHeader), HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    @Operation(summary = "Login request", tags = {"Auth"})
//    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        String[] tokens = authService.login(loginDto);
//
//        Cookie cookie = new Cookie("refreshToken", tokens[1]);
//        cookie.setHttpOnly(true);
//
//        httpServletResponse.addCookie(cookie);
//        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
//    }

}
