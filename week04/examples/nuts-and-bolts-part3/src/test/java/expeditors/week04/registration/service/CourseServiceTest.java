package expeditors.week04.registration.service;

import expeditors.week04.registration.domain.Course;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
class CourseServiceTest {
    private CourseService service;

    @BeforeEach
    void setUp() {
        service = new CourseService();
    }

    @Test
    @DisplayName("Validates course creation using constructor")
    void createCourseConstructor() {
        service.createCourse("Test Course 1", "Course-001",
                "This is the first test course");
        var courseByCode = service.getCourseByCode("Course-001");
        var courseById = service.getCourse(courseByCode.getId());
        assertEquals(courseByCode, courseById);
    }

    @Test
    @DisplayName("Validates course creation using individual attributes")
    void createCourseIndividualAttributes() {
        var course = new Course();
        course.setTitle("Test Course 2");
        course.setCode("Course-002");
        course.setDescription("This is the second test course");
        service.createCourse(course);
        var courseByCode = service.getCourseByCode("Course-002");
        var courseById = service.getCourse(courseByCode.getId());
        assertEquals(courseByCode, courseById);
    }

    @Test
    @DisplayName("Validates updates to course")
    void updateCourse() {
        service.createCourse("Test Course 3", "Course-003",
                "This is the third test course");
        var courseByCode = service.getCourseByCode("Course-003");
        courseByCode.setTitle("Test Course 3 with Updates");
        courseByCode.setDescription("This is the third test course with updates");
        service.updateCourse(courseByCode);
        var courseById = service.getCourse(courseByCode.getId());
        assertAll(() -> assertEquals(courseByCode, courseById),
                () -> assertEquals("Test Course 3 with Updates", courseById.getTitle()),
                () -> assertEquals("This is the third test course with updates", courseById.getDescription()));
    }

    @Test
    @DisplayName("Validates deletion of course")
    void deleteCourse() {
        service.createCourse("Test Course 4", "Course-004",
                "This is the fourth test course");
        var courseByCode = service.getCourseByCode("Course-004");
        assertNotNull(courseByCode);
        service.deleteCourse(courseByCode.getId());
        var repulledCourse = service.getCourseByCode("Course-004");
        assertNull(repulledCourse);
    }

    @Test
    @DisplayName("Validates getting all courses")
    void getAllCourses() {
        service.createCourse("Test Course 1", "Course-001",
                "This is the first test course");
        service.createCourse("Test Course 2", "Course-002",
                "This is the second test course");
        service.createCourse("Test Course 3", "Course-003",
                "This is the third test course");
        var courses = service.getAllCourses();
        var secondCourse = service.getCourseByCode("Course-002");
        assertAll(() -> assertEquals(3, courses.size()),
                () -> assertEquals(secondCourse, courses.get(1)));
    }

    @Test
    @DisplayName("Validates attempt to delete non-existent course")
    void deleteNonExistentCourse() {
        service.createCourse("Test Course 5", "Course-005",
                "This is the fifth test course");
        var courses = service.getAllCourses();
        assertEquals(1, courses.size());
        service.deleteCourse(100);
        var repulledCourses = service.getAllCourses();
        assertEquals(1, repulledCourses.size());
    }
}