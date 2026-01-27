package kane.zomato.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Dish Name is required")
    private String dishName;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Tax is required")
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal tax;

    @NotNull(message = "Tax is required")
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal finalPrice;


    @NotNull(message = "Hotel is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    private Boolean available = false;



    @PrePersist
    @PreUpdate
    private void calculateFinalPrice() {
        if (price != null && tax != null) {
            this.finalPrice = price.add(tax);
        }
    }
}



//| Annotation         | Layer            | Purpose               |
//        | ------------------ | ---------------- | --------------------- |
//        | `@NotNull`         | API / Validation | Friendly client error |
//        | `nullable = false` | Database         | Data integrity        |


//MenuItem owns the relationship because it contains the foreign key and is the dependent entity.
//Keeping the relation unidirectional avoids unnecessary coupling, performance issues, and serialization problems.
//Menu items are queried independently, so Hotel doesn’t need to hold a collection

//Rule of Thumb
//
//Bidirectional relationships are the exception, not the rule.
//
//Use bidirectional ONLY when:
//
//Parent must always know children
//
//Small collection
//
//Strong lifecycle dependency