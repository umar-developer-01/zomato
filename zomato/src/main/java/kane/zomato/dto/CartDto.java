package kane.zomato.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CartDto {
//    @NotEmpty
//    @Valid
//    private List<MenuDto> menuItemList;
    private Long id;
}
