package expeditors.week02.solutions.task02;

import java.util.Scanner;

/**
 * Prompts the user for a string name (ex: Ralph Westly, Ezra Aiden Pliers)
 * and then parses it into components of a name (first name, middle name,
 * and last name).  Allows the user to continue as long as they like.
 * @author Allen Sanders
 */
public class NameGame {
    public static void main(final String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            String name = null;
            boolean done = false;
            boolean inputIsValid;
            String continueResponse = null;

            while (!done) {
                System.out.print("Enter a name: ");
                name = in.nextLine();

                var nameParts = name.split(" ");
                var firstName = nameParts[0];
                var middleName = nameParts.length > 2 ? nameParts[1] : "(none)";
                var lastName = nameParts.length > 2 ? nameParts[2] : nameParts[1];

                System.out.printf("First name: %s\n", firstName);
                System.out.printf("Middle name: %s\n", middleName);
                System.out.printf("Last name: %s\n", lastName);

                do {
                    inputIsValid = true;
                    System.out.print("Do you want to enter another (y/n)? ");
                    continueResponse = in.nextLine();
                    if (continueResponse.equalsIgnoreCase("y") ||
                        continueResponse.equalsIgnoreCase("n")) {
                        done = !continueResponse.equalsIgnoreCase("y");
                    } else {
                        System.out.println("Please enter either 'y' or 'n'");
                        inputIsValid = false;
                    }
                } while (!inputIsValid);
            }
        }
    }
}
