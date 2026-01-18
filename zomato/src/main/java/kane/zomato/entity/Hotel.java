package kane.zomato.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String ownerName;

    @Column(length = 15, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    private String[] photos;

    private LocalDate dateOfRegistration;

    private LocalDate dateOfVerification;


    private Boolean active = false;

    private Boolean verified = false;

    private Boolean blocked = false;

    private Boolean acceptingOrders = false;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        return id != null && id.equals(hotel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
