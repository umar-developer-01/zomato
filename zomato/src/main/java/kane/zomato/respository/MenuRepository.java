package kane.zomato.respository;

import kane.zomato.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByDishName(String dishName);

    Optional<Menu> findByDishNameAndHotelId(String dishName, Long hotelId);
}



