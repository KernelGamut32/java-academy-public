package expeditors.week03.solutions.task01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Class used to process operations for a set of students
 * and a set of corresponding test scores.
 */
public class GradeProcessor {
    public static double calculateAverageGrade(int[] studentScores) {
        return Arrays.stream(studentScores).average().orElse(0.0);
    }

    public static GradeDetail getLowestGrade(String[] studentNames, int[] studentScores) {
        int minIndex = IntStream.range(0, studentScores.length)
                .reduce((i, j) -> studentScores[i] < studentScores[j] ? i : j)
                .orElse(-1);
        return minIndex > -1 ?
                new GradeDetail(minIndex, studentNames[minIndex],
                        studentScores[minIndex], calculateGradeLetter(studentScores[minIndex])) :
                new GradeDetail(-1, null, 0, null);
    }

    public static GradeDetail getHighestGrade(String[] studentNames, int[] studentScores) {
        int maxIndex = IntStream.range(0, studentScores.length)
                .reduce((i, j) -> studentScores[i] > studentScores[j] ? i : j)
                .orElse(-1);
        return maxIndex > -1 ?
                new GradeDetail(maxIndex, studentNames[maxIndex],
                        studentScores[maxIndex], calculateGradeLetter(studentScores[maxIndex])) :
                new GradeDetail(-1, null, 0, null);
    }

    public static GradeDetail[] sortGrades(String[] studentNames, int[] studentScores) {
        var gradeDetails = new GradeDetail[studentScores.length];
        var indices = new Integer[studentScores.length];
        for (var counter = 0; counter < studentScores.length; counter++) {
            indices[counter] = counter;
        }
        Arrays.parallelSort(indices, Comparator.comparingInt(i -> studentScores[i]));
        for (var counter = indices.length - 1; counter >= 0; counter--) {
            gradeDetails[(indices.length - 1) - counter] = new GradeDetail(indices[counter], studentNames[indices[counter]],
                    studentScores[indices[counter]], calculateGradeLetter(studentScores[indices[counter]]));
        }
        return gradeDetails;
    }

    private static String calculateGradeLetter(Integer gradeValue) {
        return switch (gradeValue) {
            case Integer g when g >= 90 -> "A";
            case Integer g when g >= 80 -> "B";
            case Integer g when g >= 70 -> "C";
            case Integer g when g >= 60 -> "D";
            default -> "F";
        };
    }
}
