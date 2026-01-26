package kane.zomato.entity;
import jakarta.validation.constraints.*;
import kane.zomato.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;



@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Email is required from Entity")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Gender is required")
    @Column(nullable = false)
    private String gender;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

//
//    @Pattern(
//            regexp = "^[6-9]\\d{9}$",
//            message = "Phone number must be a valid 10-digit Indian number"
//    )
    private String phoneNumber;


    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(nullable = false)
    private String password;


    @NotEmpty(message = "User must have at least one role")
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
