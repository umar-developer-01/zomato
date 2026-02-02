package kane.zomato.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshResponseDto
{
    private String accessToken;
}
