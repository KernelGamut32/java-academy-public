package expeditors.week03.personals;

import expeditors.week03.utility.InputHelper;

import java.util.Scanner;

/**
 * Prompts the user for personal details and outputs
 * a formatted bio on the screen.
 * @author Allen Sanders
 */
public class EvenBetterBio {
    public static void main(final String[] args) {
        System.out.println("Welcome to the EVEN BETTER bio!!");
        String firstName;
        String lastName;
        String favoriteFood;
        int numberOfHobbies;
        String[] yourHobbies;
        String animal;
        double numberOfStars;

        try (Scanner in = new Scanner(System.in)) {
            firstName = InputHelper.gatherInput(in, "Please enter your first name: ",
                    "Please provide a valid value for first name!");
            lastName = InputHelper.gatherInput(in, "Please enter your last name: ",
                    "Please provide a valid value for last name!");
            favoriteFood = InputHelper.gatherInput(in, "Please enter your favorite food: ",
                    "Please provide a valid value for favorite food!");
            numberOfHobbies = InputHelper.gatherInput(in, "How many hobbies would you like tell me about? ",
                    "Please provide a valid value for number of hobbies!", 0, 5);
            yourHobbies = new String[numberOfHobbies];
            for (var counter = 0; counter < numberOfHobbies; counter++) {
                yourHobbies[counter] = InputHelper.gatherInput(in, String.format("Please enter hobby #%d: ", counter + 1),
                        "Please provide a valid value for the hobby!");
            }
            animal = InputHelper.gatherInput(in, "If you could be any animal, what would it be? ",
                    "Please provide a value for your animal!");
            numberOfStars = InputHelper.gatherInput(in, "How many stars would you give yourself (between 0 and 5)? ",
                    "Please provide a valid value for the number of stars!", 0.0, 5.0);
            outputBioResults(firstName, lastName, favoriteFood, animal, numberOfStars, yourHobbies);
//            outputBioResults(firstName, lastName, favoriteFood, animal, numberOfStars, "Reading");
//            outputBioResults(firstName, lastName, favoriteFood, animal, numberOfStars);
        }
    }

    private static void outputBioResults(String firstName, String lastName, String favoriteFood,
                                         String animal, double numberOfStars, String... hobbies) {
        System.out.println("Here's your ENHANCED bio!!");
        String fullName = String.format("%s %s", firstName, lastName);
        System.out.printf("%s\n", fullName);
        for (var counter = 0; counter < fullName.length(); counter++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("Favorite Food: %s\n", favoriteFood);
        System.out.println("Hobbies you listed:");
        if (hobbies.length > 0) {
            for (var hobby : hobbies) {
                System.out.printf("\t%s\n", hobby);
            }
        } else {
            System.out.println("\tNo hobbies provided!");
        }
        System.out.printf("If I could choose to be any animal,\nI would be a\n\n\t%s\n", animal);
        System.out.printf("You gave yourself %.2f stars\n", numberOfStars);
    }
}
