package expeditors.week05.solid.srp.domain;

/**
 * Based on an article at <a href="https://www.geeksforgeeks.org/single-responsibility-principle-in-java-with-examples/">...</a>
 */
public class Customer {
    private String name;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }
}
