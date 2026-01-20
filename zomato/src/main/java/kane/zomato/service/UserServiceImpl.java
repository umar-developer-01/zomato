package kane.zomato.service;
import kane.zomato.dto.UserDto;
import kane.zomato.entity.User;
import kane.zomato.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDto updateUserById(Long userId, UserDto updateUserRequest) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + userId)
                );

        // Update only allowed fields
        existingUser.setEmail(updateUserRequest.getEmail());
        existingUser.setGender(updateUserRequest.getGender());
        existingUser.setPhoneNumber(updateUserRequest.getPhoneNumber());

        User updatedUser = userRepository.save(existingUser);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public User getUserById(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + userId)
                );
        return existingUser;
    }
}
