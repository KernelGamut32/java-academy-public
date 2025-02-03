package expeditors.spring.strategy;

public class StrategyPatternDemo {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Pay with Credit Card
        CreditCardPayment creditCardPayment = new CreditCardPayment();
        creditCardPayment.setCardNumber("1234-5678-9876-5432");
        creditCardPayment.setCardHolderName("Joe Schmoe");
        creditCardPayment.setExpirationMonth(5);
        creditCardPayment.setExpirationYear(2028);
        cart.setPaymentStrategy(creditCardPayment);
        cart.checkout(100.99);

        System.out.println();

        // Pay with Check
        CheckPayment checkPayment = new CheckPayment();
        checkPayment.setRoutingNumber("00000001");
        checkPayment.setAccountNumber("123456789");
        checkPayment.setAccountHolderName("Melissa Testing");
        cart.setPaymentStrategy(checkPayment);
        cart.checkout(207.34);
    }
}
