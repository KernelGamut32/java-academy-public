package expeditors.week05.solid.srp.app;

import expeditors.week05.solid.srp.domain.Address;
import expeditors.week05.solid.srp.domain.Customer;
import expeditors.week05.solid.srp.domain.Order;
import expeditors.week05.solid.srp.service.BillCalculationService;
import expeditors.week05.solid.srp.service.DeliveryService;

/**
 * Based on an article at <a href="https://www.geeksforgeeks.org/single-responsibility-principle-in-java-with-examples/">...</a>
 */
public class OrderProcessingApp {
    public static void main(String[] args) {
        OrderProcessingApp app = new OrderProcessingApp();
        Address address = app.gatherAddressDetails();
        Customer customer = app.gatherCustomerDetails(address);
        Order order = app.gatherOrderDetails(customer);

        order.prepareOrder();

        BillCalculationService billCalculationService
                = new BillCalculationService(order);
        billCalculationService.calculateBill();

        DeliveryService deliveryService = new DeliveryService(order);
        deliveryService.deliver();
    }

    private Address gatherAddressDetails() {
        Address address = new Address();
        address.setStreet("123 Rd. Ave.");
        address.setCity("New York");
        address.setState("NY");
        address.setZip("10011");
        address.setCountry("US");
        return address;
    }

    private Customer gatherCustomerDetails(Address address) {
        Customer customer = new Customer();
        customer.setName("Joe Schmoe");
        customer.setAddress(address);
        return customer;
    }

    private Order gatherOrderDetails(Customer customer) {
        Order order = new Order();
        order.setItemName("Pizza");
        order.setQuantity(2);
        order.setCustomer(customer);
        return order;
    }
}
