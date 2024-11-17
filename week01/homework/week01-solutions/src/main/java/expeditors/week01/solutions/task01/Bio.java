package expeditors.week01.solutions.task01;

public class Bio {
    public static void main(String[] args) {
        String firstName = "Allen";
        String lastName = "Sanders";
        String favoriteFood = "Chocolate Peanut Butter Ice Cream";
        String[] hobbies = { "Reading", "Video Games", "Metal Models" };
        String animal = "If I could choose to be any animal,\nI would be a\n\n\tplatypus";
        String fullName = String.format("%s %s", firstName, lastName);

        System.out.printf("%s\n", fullName);
        for (var counter = 0; counter < fullName.length(); counter++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("Favorite Food: %s\n", favoriteFood);
        System.out.println("Hobbies:");
        for (var hobby : hobbies) {
            System.out.printf("\t%s\n", hobby);
        }
        System.out.println(animal);
    }
}
