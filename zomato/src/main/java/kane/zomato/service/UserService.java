package kane.zomato.service;

import kane.zomato.dto.UserDto;
import kane.zomato.entity.User;

public interface UserService {
    UserDto updateUserById(Long userId,  UserDto userDto);
    User getUserById(Long userId);
}
