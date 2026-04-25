package kane.zomato.config;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {  // ✅ returns Spring's interface
        return new BCryptPasswordEncoder();     // ✅ BCrypt is the implementation
    }
}


