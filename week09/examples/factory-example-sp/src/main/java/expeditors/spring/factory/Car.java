package expeditors.spring.factory;

public class Car implements Vehicle {

    @Override
    public void drive() {
        System.out.println("Driving a car on the open road...");
    }
}
