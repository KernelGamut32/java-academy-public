package expeditors.week03.solutions.task01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeProcessorTest {
    private final String[] testStudentNames = { "One", "Two", "Three", "Four", "Five" };
    private final int[] testStudentScores = { 82, 94, 100, 53, 65 };

    @Test
    @DisplayName("Verifies average calculation for a set of grades")
    void calculateAverageGrade() {
        assertEquals(78.8, GradeProcessor.calculateAverageGrade(testStudentScores));
    }

    @Test
    @DisplayName("Verifies logic to retrieve lowest grade")
    void getLowestGrade() {
        assertEquals(new GradeDetail(3, "Four", 53, "F"),
                GradeProcessor.getLowestGrade(testStudentNames, testStudentScores));
    }

    @Test
    @DisplayName("Verifies logic to retrieve highest grade")
    void getHighestGrade() {
        assertEquals(new GradeDetail(2, "Three", 100, "A"),
                GradeProcessor.getHighestGrade(testStudentNames, testStudentScores));
    }

    @Test
    @DisplayName("Verifies logic to retrieve sorted list of grades")
    void sortGradesHighestToLowest() {
        var actualResult = GradeProcessor.sortGrades(testStudentNames, testStudentScores);
        assertEquals(actualResult.length, testStudentNames.length);
        assertAll(() -> assertEquals(new GradeDetail(2, "Three", 100, "A"),
                actualResult[0]),
                () -> assertEquals(new GradeDetail(1, "Two", 94, "A"),
                actualResult[1]),
                () -> assertEquals(new GradeDetail(0, "One", 82, "B"),
                actualResult[2]),
                () -> assertEquals(new GradeDetail(4, "Five", 65, "D"),
                actualResult[3]),
                () -> assertEquals(new GradeDetail(3, "Four", 53, "F"),
                actualResult[4]));
    }
}