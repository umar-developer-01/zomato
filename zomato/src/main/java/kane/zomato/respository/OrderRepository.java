package kane.zomato.respository;

import kane.zomato.entity.Menu;
import kane.zomato.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {
//    Optional<Menu> findByDishName(String dishName);
//
//    Optional<Menu> findByDishNameAndHotelId(String dishName, Long hotelId);
}



