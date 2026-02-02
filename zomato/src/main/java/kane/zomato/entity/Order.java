package kane.zomato.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import kane.zomato.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One order belongs to one user
    @NotNull(message = "User is required for placing an order")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // One order belongs to one restaurant
    @NotNull(message = "Hotel is required for placing an order")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;


    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<OrderItem> items;

    @NotNull(message = "Total amount is required")
    @PositiveOrZero(message = "Total amount cannot be negative")
    private Double totalAmount;

    @NotNull(message = "Order status is required")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}


//@NotEmpty   // collection-level rule
//@Valid      // element-level rules

//You enforce:
//
//Order must contain at least one item
//
//Each item must be valid

//| Annotation  | Applies To                      | What it checks                            |
//        | ----------- | ------------------------------- | ----------------------------------------- |
//        | `@NotNull`  | Any object                      | Value must NOT be `null`                  |
//        | `@NotEmpty` | Collections, Map, Array, String | Not `null` **AND** size > 0               |
//        | `@NotBlank` | **String only**                 | Not `null`, not empty, **not whitespace** |

