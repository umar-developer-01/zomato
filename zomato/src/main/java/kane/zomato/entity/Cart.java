package kane.zomato.entity;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One cart belongs to one user
    @NotNull(message = "User is required for placing an order")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Order should be from the same hotel
    @NotNull(message = "Hotel is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;


    // Menu Items added in the list
    @NotNull(message = "Menu Item is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menus", nullable = false)
    private List<Menu> menuItemList;
}
