package expeditors.week02.solutions.task03;

import java.util.Scanner;

/**
 * Prompts the user for an integer test score in the range 0-100
 * and then assigns a letter grade (A-F).
 * @author Allen Sanders
 */
public class Grades {
    public static void main(final String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            Integer grade = gatherInput(in, "Enter a test score: ",
                    "Invalid value provided for test score - please try again",
                    -1, 100);
            var gradeLetter = switch (grade) {
                case Integer g when g >= 90 -> "A";
                case Integer g when g >= 80 -> "B";
                case Integer g when g >= 70 -> "C";
                case Integer g when g >= 60 -> "D";
                default -> "F";
            };
            System.out.printf("Your letter grade is a %s\n", gradeLetter);
        }
    }

    private static int gatherInput(Scanner in, String prompt, String errorMessage,
                                      int lowerLimit, int upperLimit) {
        boolean inputIsValid;
        int inputValue = 0;

        do {
            inputIsValid = true;
            System.out.print(prompt);
            if (!in.hasNextInt()) {
                inputIsValid = false;
                in.next();
            } else {
                inputValue = in.nextInt();
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
