package kane.zomato.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Item name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Hotel is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    private Boolean available = true;
}


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