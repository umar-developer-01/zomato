package kane.zomato.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Dish Name is required")
    private String dishName;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    // Price must be > 0
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Tax is required")
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal tax;

    // The Total price cannot be negative
    private BigDecimal finalPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    @PrePersist
    @PreUpdate
    private void calculateFinalPrice() {
        if (price != null && tax != null) {
            this.finalPrice = price.add(tax);
        }
    }
}

//Aggregates are bidirectional
//Reference data is usually unidirectional
//
//That’s why:
//
//Order ↔ OrderItem → Bidirectional
//
//Hotel ↔ MenuItem → Often Unidirectional