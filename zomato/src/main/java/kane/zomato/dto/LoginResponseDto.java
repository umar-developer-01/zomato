package kane.zomato.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private UserDto user;
    private String accessToken;
    private String refreshToken;
}