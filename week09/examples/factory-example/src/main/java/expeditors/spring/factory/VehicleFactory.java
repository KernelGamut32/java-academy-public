package expeditors.spring.factory;

import java.util.Map;

public class VehicleFactory {

    private final Map<VehicleType, Vehicle> vehicleTypes;

    public VehicleFactory(Map<VehicleType, Vehicle> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public Vehicle getVehicle(VehicleType vehicleType) {
        return vehicleTypes.get(vehicleType);
    }
}
