package kane.zomato.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;


@Data
public class UserDto {
    private Long id;

//    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Gender is required")
    private String gender;

    private LocalDate dateOfBirth;

    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;

    private String firstName;
    private String lastName;


    // Why this matters
    //Spring internally uses:
    //authentication.getName()
    //Which calls:
    //getUsername()
    //So logs, audit trails, and security expressions depend on it.
}
