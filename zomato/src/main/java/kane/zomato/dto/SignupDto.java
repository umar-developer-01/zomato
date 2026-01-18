package kane.zomato.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupDto {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;

    @NotBlank(message = "Gender is required")
    private String gender;

}
