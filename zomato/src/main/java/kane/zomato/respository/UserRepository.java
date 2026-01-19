package kane.zomato.respository;

import kane.zomato.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}





//<S extends T> S save(S entity);
//<S extends T> Iterable<S> saveAll(Iterable<S> entities);
//
//Optional<T> findById(ID id);
//boolean existsById(ID id);
//
//Iterable<T> findAll();
//Iterable<T> findAllById(Iterable<ID> ids);
//
//long count();
//
//void deleteById(ID id);
//void delete(T entity);
//void deleteAllById(Iterable<? extends ID> ids);
//void deleteAll(Iterable<? extends T> entities);
//void deleteAll();


//Iterable<T> findAll(Sort sort);
//
//Page<T> findAll(Pageable pageable);

//Page<User> users = userRepository.findAll(PageRequest.of(0, 10));

