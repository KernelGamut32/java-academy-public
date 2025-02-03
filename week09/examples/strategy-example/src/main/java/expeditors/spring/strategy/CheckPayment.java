package expeditors.spring.strategy;

import java.text.NumberFormat;
import java.util.Locale;

public class CheckPayment implements PaymentStrategy {

    private final Locale locale = Locale.US;
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    private String routingNumber;
    private String accountNumber;
    private String accountHolderName;

    public CheckPayment() { }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    @Override
    public void pay(double amount) {
        System.out.println(this);
        System.out.printf("%s paid with checking account%n", currencyFormatter.format(amount));
    }

    @Override
    public String toString() {
        return String.format("Account Holder: %s%n", getAccountHolderName()) +
                String.format("Account Number: %s (%s)", getAccountNumber(),
                        getRoutingNumber());
    }
}
