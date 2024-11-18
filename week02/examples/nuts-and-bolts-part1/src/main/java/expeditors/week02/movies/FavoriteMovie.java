package expeditors.week02.movies;

import java.util.Scanner;
// Could also use import java.util.*;

/**
 * Allows user to enter information about their favorite
 * movie and then responds (if recognized).
 * @author Allen Sanders
 */
public class FavoriteMovie {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the Favorite Movie helper!");

        System.out.print("Please enter your full name: ");
        var fullName = in.nextLine();

        // Using an if statement to process empty input
//        if (fullName.equals("")) {
//        // Don't do this
//        // if (fullName == "") {
//        if (fullName.isEmpty()) {
//        if (fullName.trim().isEmpty()) {
//            System.out.println("Fine, don't play!!");
//            return;
//        }

        System.out.printf("Thanks %s! Now please enter the title of your favorite movie: ", fullName);
        var favoriteMovie = in.nextLine();

        // Expression format with no fallthrough
        var response = switch (favoriteMovie) {
            case "Star Wars", "The Empire Strikes Back", "Return of the Jedi" ->
            {
                System.out.println("A long time ago in a galaxy far, far away...");
                yield "May the force be with you!";
            }
            case "Jaws" -> "Duh-duh, duh-duh, duh-duh-duh-duh-duh...";
            case "The Godfather" -> "Gonna make you an offer you can't refuse";
            default -> "Sorry, I don't recognize that one :(";
        };
        // Expression format with no fallthrough
//        var response = switch (favoriteMovie.toLowerCase()) {
//            case "star wars", "the empire strikes back", "return of the jedi" ->
//            {
//                System.out.println("A long time ago in a galaxy far, far away...");
//                yield "May the force be with you!";
//            }
//            case "jaws" -> "Duh-duh, duh-duh, duh-duh-duh-duh-duh...";
//            case "the godfather" -> "Gonna make you an offer you can't refuse";
//            default -> "Sorry, I don't recognize that one :(";
//        };
        // Statement format with no fallthrough
//        String response;
//        switch (favoriteMovie) {
//            case "Star Wars", "The Empire Strikes Back", "Return of the Jedi" ->
//            {
//                System.out.println("A long time ago in a galaxy far, far away...");
//                response = "May the force be with you!";
//            }
//            case "Jaws" -> response = "Duh-duh, duh-duh, duh-duh-duh-duh-duh...";
//            case "The Godfather" -> response = "Gonna make you an offer you can't refuse";
//            default -> response = "Sorry, I don't recognize that one :(";
//        };
        // Expression format with fallthrough
//        var response = switch (favoriteMovie) {
//            case "Star Wars", "The Empire Strikes Back", "Return of the Jedi":
//            {
//                System.out.println("A long time ago in a galaxy far, far away...");
//                // yield "May the force be with you!";
//            }
//            case "Jaws":
//                yield "Duh-duh, duh-duh, duh-duh-duh-duh-duh...";
//            case "The Godfather":
//                yield "Gonna make you an offer you can't refuse";
//            default:
//                yield "Sorry, I don't recognize that one :(";
//        };
        // Statement format with fallthrough
//        String response;
//        switch (favoriteMovie) {
//            case "Star Wars", "The Empire Strikes Back", "Return of the Jedi":
//            {
//                System.out.println("A long time ago in a galaxy far, far away...");
//            }
//            case "Jaws":
//                response = "Duh-duh, duh-duh, duh-duh-duh-duh-duh...";
//                break;
//            case "The Godfather":
//                response = "Gonna make you an offer you can't refuse";
//                break;
//            default:
//                response = "Sorry, I don't recognize that one :(";
//        };
        System.out.println(response);
    }
}
