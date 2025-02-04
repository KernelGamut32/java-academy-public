package expeditors.spring.factory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryExampleApplication {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx =
			new AnnotationConfigApplicationContext(VehicleConfig.class)) {
			VehicleFactory vf = ctx.getBean("vehicleFactory", VehicleFactory.class);
			
			vf.getVehicle(VehicleType.CAR).drive();
			vf.getVehicle(VehicleType.BOAT).drive();
		}
	}
}
