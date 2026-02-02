package kane.zomato.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Order is required in the Order Item")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotBlank(message = "Item Name is required")
    private String itemName;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    // Price must be > 0
    @NotNull(message = "Price per unit is required")
    @Positive(message = "Price per unit must be greater than zero")
    private Double pricePerUnit;

    // The Total price cannot be negative
    @NotNull(message = "Total price is required")
    @Positive(message = "Total price cannot be negative or zero")
    private Double totalPrice;
}

//Aggregates are bidirectional
//Reference data is usually unidirectional
//
//That’s why:
//
//Order ↔ OrderItem → Bidirectional
//
//Hotel ↔ MenuItem → Often Unidirectional