package gts.spring.registration.config;
import gts.spring.registration.entity.Role;
import gts.spring.registration.entity.User;
import gts.spring.registration.repository.RoleRepository;
import gts.spring.registration.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
@Profile("test") // Only active during tests
public class TestDataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            Role userRole = roleRepository.findByName("USER").orElseGet(() -> roleRepository.save(Role.builder().name("USER").build()));
            Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> roleRepository.save(Role.builder().name("ADMIN").build()));

            User user = new User();
            user.setUsername("testuser");
            user.setPassword(new BCryptPasswordEncoder().encode("password"));
            user.setRoles(Set.of(userRole));
            userRepository.save(user);

            User admin = new User();
            admin.setUsername("testadmin");
            admin.setPassword(new BCryptPasswordEncoder().encode("adminpassword"));
            admin.setRoles(Set.of(userRole, adminRole));
            userRepository.save(admin);
        };
    }
}
