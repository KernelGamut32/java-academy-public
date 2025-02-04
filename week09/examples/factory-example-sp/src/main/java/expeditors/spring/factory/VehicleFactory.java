package expeditors.spring.factory;

import java.util.Map;

public class VehicleFactory {

    public Vehicle getVehicle(VehicleType vehicleType) {
        if (vehicleType == VehicleType.CAR) {
            return new Car();
        } else if (vehicleType == VehicleType.BOAT) {
            return new Boat();
        }
        return null;
    }
}
