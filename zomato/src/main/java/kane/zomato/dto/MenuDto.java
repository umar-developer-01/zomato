package kane.zomato.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MenuDto {

    private Long id;

    @NotBlank(message = "Dish name is required")
    private String dishName;

    @NotBlank(message = "Department name is required")
    private String department;

    @NotBlank(message = "Price is required")
    private Double price;

    @NotBlank(message = "Tax is required")
    private Double tax;

    @NotBlank(message = "Final Price is required")
    private Double finalPrice;

    @NotNull(message = "Hotel Id is required")
    private Long hotelId;

    @NotNull(message = "Available is required")
    private Boolean available;

}
