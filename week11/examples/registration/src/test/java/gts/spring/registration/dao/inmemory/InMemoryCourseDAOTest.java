package gts.spring.registration.dao.inmemory;

import gts.spring.registration.domain.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
public class InMemoryCourseDAOTest {

    private Course course1;
    private Course course2;

    private InMemoryCourseDAO dao = new InMemoryCourseDAO();

    @BeforeEach
    void setUp() {
        dao = new InMemoryCourseDAO();
        dao.resetDataStore();

        course1 = createTestCourse("Intro to Math", "M101", 1.5);
        course2 = createTestCourse("Intro to Physics", "P101");

        course1 = dao.insert(course1);
        course2 = dao.insert(course2);
    }

    @Test
    public void testFindAllCourses() {
        // Arrange - handled in setUp
        // Act
        List<Course> courses = dao.findAll();
        // Assert
        assertAll(
                () -> assertNotNull(courses),
                () -> assertEquals(2, courses.size()),
                () -> assertEquals(0, courses.stream().filter(course -> course.getId() == 0).count()),
                () -> assertEquals(course1, courses.getFirst()),
                () -> assertEquals(course2, courses.get(1))
        );
    }

    @Test
    public void testFindCourseById() {
        // Arrange - handled in setUp
        // Act
        Optional<Course> foundCourse = dao.findById(course1.getId());
        // Assert
        assertTrue(foundCourse.isPresent());
        assertEquals(course1, foundCourse.get());
    }

    @Test
    public void testUpdateCourse() {
        // Arrange
        Course courseToUpdate = Course.builder()
                .title("Intermediate Physics")
                .code("P102")
                .credits(4.5)
                .build();
        courseToUpdate.setId(course2.getId());
        // Act
        boolean updated = dao.update(courseToUpdate);
        Optional<Course> verifyCourse = dao.findById(course2.getId());
        // Assert
        assertTrue(verifyCourse.isPresent());
        assertTrue(updated);
        assertNotEquals(course2.getTitle(), verifyCourse.get().getTitle());
        assertNotEquals(course2.getCode(), verifyCourse.get().getCode());
        assertNotEquals(course2.getCredits(), verifyCourse.get().getCredits());
        assertEquals(courseToUpdate, verifyCourse.get());
    }

    @Test
    public void testDeleteCourse() {
        // Arrange - handled in setUp
        // Act
        boolean deleted = dao.delete(course1);
        List<Course> remainingCourses = dao.findAll();
        // Assert
        assertAll(
                () -> assertTrue(deleted),
                () -> assertNotNull(remainingCourses),
                () -> assertEquals(1, remainingCourses.size()),
                () -> assertEquals(course2, remainingCourses.getFirst())
        );
    }

    @Test
    public void testAttemptToUpdateNonExistentCourse() {
        // Arrange
        Course nonExistentCourse = Course.builder().build();
        nonExistentCourse.setId(-1L);
        // Act
        boolean updated = dao.update(nonExistentCourse);
        // Assert
        assertFalse(updated);
    }

    @Test
    public void testAttemptToDeleteNonExistentCourse() {
        // Arrange
        Course nonExistentCourse = Course.builder().build();
        nonExistentCourse.setId(-1L);
        // Act
        boolean deleted = dao.delete(nonExistentCourse);
        // Assert
        assertFalse(deleted);
    }

    private Course createTestCourse(String title, String code) {
        return Course.builder()
                            .title(title)
                            .code(code)
                            .build();
    }

    private Course createTestCourse(String title, String code, double credits) {
        Course course = createTestCourse(title, code);
        course.setCredits(credits);
        return course;
    }
}
