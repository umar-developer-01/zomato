package kane.zomato.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import kane.zomato.enums.ERole;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true, nullable = false)
    private ERole name;  // Enum: ROLE_USER, ROLE_ADMIN, ROLE_CASHIER

    // getters/setters
}
