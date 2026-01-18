package kane.zomato.dto;
import kane.zomato.enums.Gender;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;

    private String userName;
    private String email;

    private Gender gender;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String firstName;
    private String lastName;
}
