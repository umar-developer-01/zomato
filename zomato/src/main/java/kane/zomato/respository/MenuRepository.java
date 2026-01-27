package kane.zomato.respository;

import kane.zomato.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface MenuRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByDishName(String dishName);
}



