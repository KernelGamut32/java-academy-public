package expeditors.week02.solutions.task01;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Prompts the user for two double precision numbers and
 * calculates/displays the results of performing four different
 * mathematical operations (add, subtract, multiply, divide).
 * @author Allen Sanders
 */
public class Calculator {
    public static void main(final String[] args) {
        // final keyword prevents reassignment to parameter
        // args = new String[] { "Test1" };

        double operand1, operand2;
        double sum, difference, product, quotient;
        try (Scanner in = new Scanner(System.in)) {
            operand1 = gatherInput(in, "Enter 1st number: ",
                    "Invalid value provided for 1st number - please try again",
                    0.0, 100.0);
            operand2 = gatherInput(in, "Enter 2nd number: ",
                    "Invalid value provided for 2nd number - please try again",
                    0.0, 100.0);
            sum = operand1 + operand2;
            difference = operand1 - operand2;
            product = operand1 * operand2;
            quotient = operand1 / operand2;

            DecimalFormat df = new DecimalFormat("#.###");
            System.out.printf("%s + %s = %s\n", operand1, operand2, df.format(sum));
            System.out.printf("%s - %s = %s\n", operand1, operand2, df.format(difference));
            System.out.printf("%s * %s = %s\n", operand1, operand2, df.format(product));
            System.out.printf("%s / %s = %s\n", operand1, operand2, df.format(quotient));
        }
    }

    private static double gatherInput(Scanner in, String prompt, String errorMessage,
                               double lowerLimit, double upperLimit) {
        boolean inputIsValid;
        double inputValue = 0.0;

        do {
            inputIsValid = true;
            System.out.print(prompt);
            if (!in.hasNextDouble()) {
                inputIsValid = false;
                in.next();
            } else {
                inputValue = in.nextDouble();
            }
            if (inputValue <= lowerLimit || inputValue > upperLimit) {
                System.out.println(errorMessage);
                inputIsValid = false;
            }
        } while (!inputIsValid);
        in.nextLine();
        return inputValue;
    }
}
