package expeditors.spring.factory;

public class Boat implements Vehicle {
    
    @Override
    public void drive() {
        System.out.println("Steering a boat on the open sea...");
    }
}
