package expeditors.week02.financials;

//import java.math.BigDecimal;
//import java.text.NumberFormat;
//import java.util.Locale;
import java.util.Scanner;

/**
 * Allows user to enter a sales amount and commission rate and
 * calculates/displays resulting commission.
 * @author Allen Sanders
 * Try with:
 * - 20000 and .25
 * - 99999.15499878754656431321654654 and .49554786210012785631244678987 (and 18 decimal places)
 */
public class CommissionCalculator {
    public static void main(String[] args) {
        System.out.println("Welcome to the Commission Calculator!!");
        double salesAmount = 0.0;
        double commissionRate = 0.0;
        double commissionAmount;
//        BigDecimal commissionAmount;
        boolean inputIsValid;

//        Locale locale = Locale.US;
//        Locale locale = Locale.CHINA;
//        Locale locale = Locale.ITALY;
//        Locale locale = Locale.UK;
//        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
//        NumberFormat percentFormat = NumberFormat.getPercentInstance(locale);

        Scanner in = new Scanner(System.in);

        do {
            inputIsValid = true;
            System.out.print("What was the amount of the sale? ");
            if (!in.hasNextDouble()) {
                inputIsValid = false;
                in.next();
            } else {
                salesAmount = in.nextDouble();
            }
            if (salesAmount <= 0.0 || salesAmount > 100000.0) {
                System.out.println("That is an invalid value for sales amount!");
                inputIsValid = false;
            }
        } while (!inputIsValid);

        do {
            inputIsValid = true;
            System.out.print("What is your rate of commission? ");
            if (!in.hasNextDouble()) {
                inputIsValid = false;
                in.next();
            } else {
                commissionRate = in.nextDouble();
            }
            if (commissionRate <= 0.02 || commissionRate > 0.50) {
                System.out.println("That is an invalid value for commission rate!");
                inputIsValid = false;
            }
        } while (!inputIsValid);

        commissionAmount = salesAmount * commissionRate;
//        commissionAmount = BigDecimal.valueOf(salesAmount).multiply(BigDecimal.valueOf(commissionRate));
        System.out.printf("On a sales amount of $%.2f\n", salesAmount);
//        System.out.printf("On a sales amount of %s\n", currencyFormat.format(salesAmount));
        System.out.printf("With a commission rate of %.4f\n", commissionRate);
//        System.out.printf("With a commission rate of %s\n", percentFormat.format(commissionRate));
        System.out.printf("You will earn a commission of $%.2f\n", commissionAmount);
//        System.out.printf("You will earn a commission of %s\n", currencyFormat.format(commissionAmount));
//        System.out.printf("You will earn a commission of $%.18f\n", commissionAmount);
    }
}
