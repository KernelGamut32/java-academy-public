package expeditors.spring.strategy;

import java.text.NumberFormat;
import java.util.Locale;

public class CreditCardPayment implements PaymentStrategy {

    private final Locale locale = Locale.US;
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    private String cardNumber;
    private String cardHolderName;
    private Integer expirationMonth;
    private Integer expirationYear;

    public CreditCardPayment() { }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }

    @Override
    public void pay(double amount) {
        System.out.println(this);
        System.out.printf("%s paid with credit card%n", currencyFormatter.format(amount));
    }

    @Override
    public String toString() {
        return String.format("Card Number: %s%n", getCardNumber()) +
                String.format("Card Holder Name: %s%n", getCardHolderName()) +
                String.format("Expiration Date: %02d/%d", getExpirationMonth(),
                        getExpirationYear());
    }
}
