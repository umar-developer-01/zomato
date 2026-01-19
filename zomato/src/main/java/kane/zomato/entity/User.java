package kane.zomato.entity;
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



    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String gender;

    private LocalDate dateOfBirth;

    private String phoneNumber;


    @Column(nullable = false)
    private String password;


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
