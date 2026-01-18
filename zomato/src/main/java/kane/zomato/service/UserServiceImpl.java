package kane.zomato.service;
import kane.zomato.dto.UserDto;
import kane.zomato.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDto updateUserById(Long userId, UserDto updateUserRequest) {
        return null;
    }

    @Override
    public UserDto getUserById(Long userId) {
        return null;
    }
}
