package expeditors.week05.solid.srp.service;

import expeditors.week05.solid.srp.domain.Order;

/**
 * Based on an article at <a href="https://www.geeksforgeeks.org/single-responsibility-principle-in-java-with-examples/">...</a>
 */
public class DeliveryService {
    private final Order order;

    public DeliveryService(Order order) {
        this.order = order;
    }

    public void deliver() {
        // Here, we would want to interface with another
        // system which actually assigns the task of
        // delivery to different persons
        // based on location, etc.
        System.out.println("=".repeat(80));
        System.out.println("Delivering the order");
        System.out.println("=".repeat(80));
        System.out.println(
                "Order with order id "
                        + this.order.getOrderId()
                        + " being delivered to "
                        + this.order.getCustomer().getName());
        System.out.println(
                "Order is to be delivered to:\n"
                        + this.order.getCustomer().getAddress());
    }
}
