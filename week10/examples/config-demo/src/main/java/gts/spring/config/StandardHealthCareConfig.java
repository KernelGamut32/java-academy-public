package gts.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
public class StandardHealthCareConfig {

    @Value("Dr. John Dorian")
    private String name;
    @Value("123 Rd. Blvd.")
    private String address;
    @Value("20")
    private int yearsOfExperience;

    @Bean
    @Lazy
    @Qualifier("standardHealthCareProvider")
    public StandardHealthCareProvider standardHealthCareProvider() {
        StandardHealthCareProvider provider = new StandardHealthCareProvider();
        provider.setName(name);
        provider.setAddress(address);
        provider.setYearsOfExperience(yearsOfExperience);
        return provider;
    }
}
