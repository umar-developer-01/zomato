package kane.zomato.config;

import kane.zomato.entity.Role;
import kane.zomato.enums.RoleE;
import kane.zomato.respository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            for (RoleE roleE : RoleE.values()) {
                if (roleRepository.findByName(roleE).isEmpty()) {
                    Role role = new Role();
                    role.setName(roleE);
                    roleRepository.save(role);
                }
            }
        };
    }
}