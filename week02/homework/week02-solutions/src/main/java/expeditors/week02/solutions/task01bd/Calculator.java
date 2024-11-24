package expeditors.week02.solutions.task01bd;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Prompts the user for two double precision numbers and
 * calculates/displays the results of performing four different
 * mathematical operations (add, subtract, multiply, divide).
 * Modified to use BigDecimal.
 * @author Allen Sanders
 */
public class Calculator {
    public static void main(final String[] args) {
        BigDecimal operand1, operand2;
        BigDecimal sum, difference, product, quotient;
        try (Scanner in = new Scanner(System.in)) {
            operand1 = gatherInput(in, "Enter 1st number: ",
                    "Invalid value provided for 1st number - please try again",
                    BigDecimal.ZERO, BigDecimal.valueOf(100.0));
            operand2 = gatherInput(in, "Enter 2nd number: ",
                    "Invalid value provided for 2nd number - please try again",
                    BigDecimal.ZERO, BigDecimal.valueOf(100.0));
            sum = operand1.add(operand2);
            difference = operand1.subtract(operand2);
            product = operand1.multiply(operand2);
            quotient = operand1.divide(operand2, RoundingMode.HALF_UP);

            DecimalFormat df = new DecimalFormat("#.###");
            System.out.printf("%s + %s = %s\n", operand1, operand2, df.format(sum));
            System.out.printf("%s - %s = %s\n", operand2, operand1, df.format(difference));
            System.out.printf("%s * %s = %s\n", operand2, operand1, df.format(product));
            System.out.printf("%s / %s = %s\n", operand2, operand1, df.format(quotient));
        }
    }

    private static BigDecimal gatherInput(Scanner in, String prompt, String errorMessage,
                                      BigDecimal lowerLimit, BigDecimal upperLimit) {
        boolean inputIsValid;
        BigDecimal inputValue = BigDecimal.ZERO;

        do {
            inputIsValid = true;
            System.out.print(prompt);
            if (!in.hasNextDouble()) {
                inputIsValid = false;
                in.next();
            } else {
                inputValue = in.nextBigDecimal();
            }
            if ((inputValue.compareTo(lowerLimit) == 0 ||
                    inputValue.compareTo(lowerLimit) < 0)||
                    inputValue.compareTo(upperLimit) > 0) {
                System.out.println(errorMessage);
                inputIsValid = false;
            }
        } while (!inputIsValid);
        in.nextLine();
        return inputValue;
    }
}
