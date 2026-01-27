package kane.zomato.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuDto {

    private Long id;

    @NotBlank(message = "Dish name is required")
    private String dishName;

    @NotBlank(message = "Department name is required")
    private String department;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal price;

    @NotNull(message = "Tax is required")
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal tax;

    private BigDecimal finalPrice;

    @NotNull(message = "Hotel Id is required")
    private Long hotelId;

    private Boolean available;

}
