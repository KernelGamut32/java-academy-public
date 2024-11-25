package expeditors.week03.utility;

import java.util.Scanner;

/**
 * Input helper providing several overloads of a method
 * that can be used to prompt for different types of validated input.
 * @author Allen Sanders
 */
public class InputHelper {
    public static String gatherInput(Scanner consoleInput, String prompt,
                                     String errorMessage) {
        boolean inputIsValid;
        String inputValue;

        do {
            inputIsValid = true;
            System.out.print(prompt);
            inputValue = consoleInput.nextLine();
            if (inputValue.trim().isEmpty()) {
                System.out.println(errorMessage);
                inputIsValid = false;
            }
        } while (!inputIsValid);

        return inputValue;
    }

    public static int gatherInput(Scanner consoleInput, String prompt, String errorMessage,
                                  int lowerLimit, int upperLimit) {
        boolean inputIsValid;
        int inputValue = lowerLimit;

        do {
            inputIsValid = true;
            System.out.print(prompt);
            if (!consoleInput.hasNextInt()) {
                inputIsValid = false;
                consoleInput.next();
            } else {
                inputValue = consoleInput.nextInt();
            }
            if (inputValue <= lowerLimit || inputValue > upperLimit) {
                System.out.println(errorMessage);
                inputIsValid = false;
            }
        } while (!inputIsValid);
        consoleInput.nextLine();
        return inputValue;
    }

    public static double gatherInput(Scanner consoleInput, String prompt, String errorMessage,
                                     double lowerLimit, double upperLimit) {
        boolean inputIsValid;
        double inputValue = lowerLimit;

        do {
            inputIsValid = true;
            System.out.print(prompt);
            if (!consoleInput.hasNextDouble()) {
                inputIsValid = false;
                consoleInput.next();
            } else {
                inputValue = consoleInput.nextDouble();
            }
            if (inputValue <= lowerLimit || inputValue > upperLimit) {
                System.out.println(errorMessage);
                inputIsValid = false;
            }
        } while (!inputIsValid);
        consoleInput.nextLine();
        return inputValue;
    }
}
