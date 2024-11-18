package expeditors.week02.personals;

import java.util.*;

/**
 * Allows user to enter information about themselves
 * for echo back in bio format.
 * @author Allen Sanders
 */
public class BetterBio {
    public static void main(String[] args) {
        System.out.println("Welcome to the BETTER bio!!");
        String firstName;
        String lastName;
        String favoriteFood;
        String oneOfYourHobbies;
        String animal;
        int numberOfStars;
//        int numberOfStars = 0;
        boolean inputIsValid;

        Scanner in = new Scanner(System.in);

        do {
            inputIsValid = true;
            System.out.print("Please enter your first name: ");
            firstName = in.nextLine();
            if (firstName.trim().isEmpty()) {
                System.out.println("Please provide a value for first name!");
                inputIsValid = false;
            }
        } while (!inputIsValid);

        do {
            inputIsValid = true;
            System.out.print("Please enter your last name: ");
            lastName = in.nextLine();
            if (lastName.trim().isEmpty()) {
                System.out.println("Please provide a value for last name!");
                inputIsValid = false;
            }
        } while (!inputIsValid);

        do {
            inputIsValid = true;
            System.out.print("Please enter your favorite food: ");
            favoriteFood = in.nextLine();
            if (favoriteFood.trim().isEmpty()) {
                System.out.println("Please provide a value for favorite food!");
                inputIsValid = false;
            }
        } while (!inputIsValid);

        do {
            inputIsValid = true;
            System.out.print("Please enter one of your hobbies: ");
            oneOfYourHobbies = in.nextLine();
            if (oneOfYourHobbies.trim().isEmpty()) {
                System.out.println("Please provide a value for your hobby!");
                inputIsValid = false;
            }
        } while (!inputIsValid);

        do {
            inputIsValid = true;
            System.out.print("If you could be any animal, what would it be? ");
            animal = in.nextLine();
            if (animal.trim().isEmpty()) {
                System.out.println("Please provide a value for your animal!");
                inputIsValid = false;
            }
        } while (!inputIsValid);

        do {
            inputIsValid = true;
            System.out.print("How many stars do you think you should have? ");
//            while (!in.hasNextInt()) {
//                System.out.println("That is an invalid option for number of stars!");
//                System.out.print("How many stars do you think you should have? ");
//                in.next(); // Clear the invalid input
//            }
            numberOfStars = in.nextInt();
//            if (!in.hasNextInt()) {
//                inputIsValid = false;
//                in.next();
//            } else {
//                numberOfStars = in.nextInt();
//            }
            if (numberOfStars == 0 || numberOfStars > 10) {
                System.out.println("That is an invalid option for number of stars!");
                inputIsValid = false;
            } else if (numberOfStars == 1) {
                System.out.println("Come on, you deserve more than that!");
                inputIsValid = false;
            }
        } while (!inputIsValid);

        System.out.println("Here's your bio!!");
        String fullName = String.format("%s %s", firstName, lastName);
        System.out.printf("%s\n", fullName);
        for (var counter = 0; counter < fullName.length(); counter++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("Favorite Food: %s\n", favoriteFood);
        System.out.println("One of your hobbies:");
        System.out.printf("Your Hobby: %s\n", oneOfYourHobbies);
        System.out.printf("If I could choose to be any animal,\nI would be a\n\n\t%s\n", animal);
        System.out.printf("You got %d stars: ", numberOfStars);
        for (var counter = 0; counter < numberOfStars; counter++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
