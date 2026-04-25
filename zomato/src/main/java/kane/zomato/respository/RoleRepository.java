package kane.zomato.respository;

import kane.zomato.entity.Role;
import kane.zomato.enums.RoleE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleE name);
}