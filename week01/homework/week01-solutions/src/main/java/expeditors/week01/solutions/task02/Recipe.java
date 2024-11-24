package expeditors.week01.solutions.task02;

import java.util.ArrayList;

public class Recipe {
    private static final String CUP_MEASURE_TYPE = "Cup(s)";

    public static void main(String[] args) {
        String title = "Absolutely the Best Chocolate Chip Cookies";
        String submitter = "NICOLEFAUSTHUNT";
        int prepTimeHours = 0;
        int prepTimeMins = 10;
        int cookTimeHours = 0;
        int cookTimeMins = 10;
        int totalHours = prepTimeHours + cookTimeHours;
        int totalMins = prepTimeMins + cookTimeMins;
        int servings = 24;
        int yield = 24;
        String yieldDisplay = String.format("%d Cookies", yield);

        ArrayList<Ingredient> subsetOfIngredients = new ArrayList<>();
        var shortening = new Ingredient(1.0, CUP_MEASURE_TYPE, "Butter Flavored Shortening");
//        shortening.setAmount(1.0);
//        shortening.setMeasureType(CUP_MEASURE_TYPE);
//        shortening.setName("Butter Flavored Shortening");
        subsetOfIngredients.add(shortening);
        var whiteSugar = new Ingredient();
        whiteSugar.setAmount(0.75);
        whiteSugar.setMeasureType(CUP_MEASURE_TYPE);
        whiteSugar.setName("White Sugar");
        subsetOfIngredients.add(whiteSugar);
        var eggs = new Ingredient();
        eggs.setAmount(2);
        eggs.setMeasureType(null);
        eggs.setName("Large Eggs");
        subsetOfIngredients.add(eggs);
        var flour = new Ingredient();
        flour.setAmount(2.25);
        flour.setMeasureType(CUP_MEASURE_TYPE);
        flour.setName("All-Purpose Flour");
        subsetOfIngredients.add(flour);

        System.out.printf("%s Submitted By %s\n\n", title, submitter);
        System.out.printf("Prep Time: %d hour(s) %d minute(s)\t", prepTimeHours, prepTimeMins);
        System.out.printf("Cook Time: %d hour(s) %d minute(s)\t", cookTimeHours, cookTimeMins);
        System.out.printf("Total Time: %d hour(s) %d minute(s)\n", totalHours, totalMins);
        System.out.printf("Servings: %d\t", servings);
        System.out.printf("Yield: %s\n", yieldDisplay);
        System.out.println();
        System.out.println("Ingredients:");
        for (var ingredient : subsetOfIngredients) {
            System.out.printf("\t%s\n", ingredient);
        }
    }
}
