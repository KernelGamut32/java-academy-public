package expeditors.spring.factory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleConfig {

    @Bean
    @Qualifier("car")
    public Vehicle car() {
        return new Car();
    }

    @Bean
    @Qualifier("boat")
    public Boat boat() {
        return new Boat();
    }

    @Bean
    public Map<VehicleType, Vehicle> vehicleTypes(@Qualifier("car") Vehicle car, @Qualifier("boat") Vehicle boat) {
        return Map.of(
                VehicleType.CAR, car,
                VehicleType.BOAT, boat
        );
    }

    @Bean
    public VehicleFactory vehicleFactory(Map<VehicleType, Vehicle> vehicleTypes) {
        return new VehicleFactory(vehicleTypes);
    }
}
