package kane.zomato.service;

import kane.zomato.dto.UserDto;

public interface UserService {
    UserDto updateUserById(Long userId,  UserDto userDto);
    UserDto getUserById(Long userId);
}
