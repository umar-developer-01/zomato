package kane.zomato.entity;

import jakarta.persistence.*;
import kane.zomato.enums.RoleE;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true, nullable = false)
    private RoleE name;  // Enum: ROLE_USER, ROLE_ADMIN, ROLE_CASHIER

    // getters/setters
}
