package expeditors.week03.solutions.task01;

import expeditors.week03.solutions.utility.InputHelper;

import java.util.Scanner;

/**
 * Prompts the user for a list of students and their test scores. Once entered, users
 * can interact with a menu that allows them to make specific queries of the data. All
 * data currently stored in memory.
 * @author Allen Sanders
 */
public class StudentScoreManager {
    private final static String INPUT_REGEX = "[a-zA-Z]+,\\d+";
    private final static String MENU_TEXT = """
            Menu:
            1 - Display the average test score
            2 - Display the student with the highest score
            3 - Display the student with the lowest score
            4 - Display sorted scores (highest to lowest)
            5 - Quit
            """;

    public static void main(final String[] args) {
        System.out.println("Welcome to the Student Score Manager!");
        int numberOfStudents;
        String[] studentNames;
        int[] studentScores;
        int menuSelection;

        try (Scanner in = new Scanner(System.in)) {
            numberOfStudents = InputHelper.gatherInput(in, "How many students do you want to process scores for? ",
                    "Please provide a valid value for number of students (greater than 0 and less than or equal to 10)!",
                    0, 10);
            studentNames = new String[numberOfStudents];
            studentScores = new int[numberOfStudents];
            System.out.println("Enter a student name and score separated by a comma.");
            for (var counter = 0; counter < numberOfStudents; counter++) {
                parseAndStoreStudentDataInput(InputHelper.gatherInput(in,
                        String.format("Enter student #%d name and score: ", counter + 1),
                        "Please provide a valid value for name and score (<name>,<score>)!",INPUT_REGEX),
                        counter, studentNames, studentScores);
            }
            System.out.println(MENU_TEXT);
            do {
                menuSelection = InputHelper.gatherInput(in, "Enter command: ",
                        "Please enter one of the valid menu options!", 0, 5);
                executeSelectedQuery(menuSelection, studentNames, studentScores);
            } while (menuSelection != 5);
        }
    }

    private static void parseAndStoreStudentDataInput(String dataToParse, int indexToPopulate,
                                              String[] studentNames, int[] studentScores) {
        String[] parsedElements = dataToParse.split(",");
        studentNames[indexToPopulate] = parsedElements[0].trim();
        studentScores[indexToPopulate] = Integer.parseInt(parsedElements[1].trim());
    }

    private static void executeSelectedQuery(int menuSelection, String[] studentNames, int[] studentScores) {
        switch (menuSelection) {
            case 1 -> displayAverage(studentScores);
            case 2 -> displayHighestScore(studentNames, studentScores);
            case 3 -> displayLowestScore(studentNames, studentScores);
            case 4 -> displaySortedScores(studentNames, studentScores);
        }
    }

    private static void displayAverage(int[] studentScores) {
        System.out.printf("The average of all %d scores is %.2f\n",
                studentScores.length, GradeProcessor.calculateAverageGrade(studentScores));
    }

    private static void displayHighestScore(String[] studentNames, int[] studentScores) {
        var highestGradeDetail = GradeProcessor.getHighestGrade(studentNames, studentScores);
        if (highestGradeDetail.index() >= 0) {
            System.out.printf("%s\n", highestGradeDetail);
        } else {
            System.out.println("Unable to determine highest grade!");
        }
    }

    private static void displayLowestScore(String[] studentNames, int[] studentScores) {
        var lowestGradeDetail = GradeProcessor.getLowestGrade(studentNames, studentScores);
        if (lowestGradeDetail.index() >= 0) {
            System.out.printf("%s\n", lowestGradeDetail);
        } else {
            System.out.println("Unable to determine lowest grade!");
        }
    }

    private static void displaySortedScores(String[] studentNames, int[] studentScores) {
        var gradeDetails = GradeProcessor.sortGrades(studentNames, studentScores);
        System.out.println("Sorted grades - highest to lowest");
        for (var gradeDetail : gradeDetails) {
            System.out.printf("%s\n", gradeDetail);
        }
    }
}
