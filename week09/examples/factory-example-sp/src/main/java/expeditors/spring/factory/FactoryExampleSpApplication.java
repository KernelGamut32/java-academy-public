package expeditors.spring.factory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class FactoryExampleSpApplication {

	public static void main(String[] args) {
		VehicleFactory vf = new VehicleFactory();

		vf.getVehicle(VehicleType.CAR).drive();
		vf.getVehicle(VehicleType.BOAT).drive();
	}
}
