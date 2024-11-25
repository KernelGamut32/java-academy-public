package expeditors.week03.grades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeHelperTest {
    private GradeHelper helper;

    @BeforeEach
    void setUp() {
        helper = new GradeHelper();
    }

    @Test
    @DisplayName("Test A grade values")
    void caculateGradeLetterForA() {
        assertEquals("A", helper.caculateGradeLetter(90));
        assertEquals("A", helper.caculateGradeLetter(100));
        assertEquals("A", helper.caculateGradeLetter(95));
        assertNotEquals("A", helper.caculateGradeLetter(89));
    }

    @Test
    @DisplayName("Test B grade values")
    void caculateGradeLetterForB() {
        assertAll(() -> assertEquals("B", helper.caculateGradeLetter(80)),
                () -> assertEquals("B", helper.caculateGradeLetter(89)),
                () -> assertEquals("B", helper.caculateGradeLetter(85)),
                () -> assertNotEquals("B", helper.caculateGradeLetter(79)));
    }

    @Test
    @DisplayName("Test C grade values")
    void caculateGradeLetterForC() {
        assertAll(() -> assertEquals("C", helper.caculateGradeLetter(70)),
                () -> assertEquals("C", helper.caculateGradeLetter(79)),
                () -> assertEquals("C", helper.caculateGradeLetter(75)),
                () -> assertNotEquals("C", helper.caculateGradeLetter(69)));
    }

    @Test
    @DisplayName("Test D grade values")
    void caculateGradeLetterForD() {
        assertAll(() -> assertEquals("D", helper.caculateGradeLetter(60)),
                () -> assertEquals("D", helper.caculateGradeLetter(69)),
                () -> assertEquals("D", helper.caculateGradeLetter(65)),
                () -> assertNotEquals("D", helper.caculateGradeLetter(59)));
    }

    @Test
    @DisplayName("Test F grade values")
    void caculateGradeLetterForF() {
        assertAll(() -> assertEquals("F", helper.caculateGradeLetter(59)),
                () -> assertEquals("F", helper.caculateGradeLetter(0)),
                () -> assertEquals("F", helper.caculateGradeLetter(25)),
                () -> assertNotEquals("F", helper.caculateGradeLetter(100)));
    }
}