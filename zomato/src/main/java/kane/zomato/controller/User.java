package kane.zomato.controller;

import jakarta.validation.Valid;
import kane.zomato.dto.UserDto;
import kane.zomato.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class User {

    private final UserServiceImpl userService;


    @PutMapping("/updateUserById/{userId}")
    @Operation(summary = "Update an existing User", tags = {"Update an existing User"})
    public ResponseEntity<UserDto> updateUserById(@PathVariable Long userId, @Valid @RequestBody UserDto updateUserRequest) {
        log.info("Update an existing User: {}",  updateUserRequest);
        return new ResponseEntity<>(userService.updateUserById(userId,updateUserRequest), HttpStatus.CREATED);
    }

//    @GetMapping("getUserById/{userId}")
//    @Operation(summary = "Get an existing User", tags = {"Fetch Existing User"})
//    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
//        log.info("Get an existing User: {}", userId);
//        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.CREATED);
//    }


}
