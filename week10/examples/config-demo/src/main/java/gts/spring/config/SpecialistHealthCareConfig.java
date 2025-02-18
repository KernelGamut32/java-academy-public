package gts.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
//@PropertySource("classpath:application.properties")
public class SpecialistHealthCareConfig {

    @Value("#{dataContainer.DOCTOR_NAME}")
//    @Value("${doctor.name}")
    private String name;
    @Value("#{dataContainer.DOCTOR_ADDRESS}")
//    @Value("${doctor.address}")
    private String address;
    @Value("#{dataContainer.DOCTOR_YEARS_OF_EXPERIENCE}")
//    @Value("${doctor.years_of_experience}")
    private int yearsOfExperience;
    @Value("#{dataContainer.DOCTOR_SPECIALTY}")
//    @Value("${doctor.specialty}")
    private String specialty;

    @Bean
    @Lazy
    @Qualifier("specialistHealthCareProvider")
    public SpecialistHealthCareProvider specialistHealthCareProvider() {
        SpecialistHealthCareProvider provider = new SpecialistHealthCareProvider();
        provider.setName(name);
        provider.setAddress(address);
        provider.setYearsOfExperience(yearsOfExperience);
        provider.setSpecialty(specialty);
        return provider;
    }
}
