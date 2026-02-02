package kane.zomato.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    @NotBlank(message = "Dish name is required")
    private String dishName;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal price;

    @NotNull(message = "Tax is required")
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal tax;

}
