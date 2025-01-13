package expeditors.week07.registration.service;

import expeditors.week07.registration.dao.CourseDAO;
import expeditors.week07.registration.domain.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseDAO courseDAOMock;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void givenNewCourseWithIndividualProperties_whenCreated_thenCreationSucceeds() {
        // given
        var testCourse = buildTestCourse(42, "Test Course", "TT01", "Verifying creation");

        // when
        courseService.createCourse("Test Course", "TT01", "Verifying creation");

        // then
        then(courseDAOMock).should().create(testCourse);
    }

    @Test
    public void givenNewCourse_whenCreated_thenCreationSucceeds() {
        // given
        var testCourse = buildTestCourse(42, "Test Course", "TT01", "Verifying creation");

        // when
        courseService.createCourse(new Course("Test Course", "TT01", "Verifying creation"));

        // then
        then(courseDAOMock).should().create(testCourse);
    }

    @Test
    public void givenInvalidCourseWithInvalidTitle_whenCreated_thenExceptionIsThrown() {
        // given

        // when & then
        IllegalArgumentException nullException = assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse(null, "TT02", "Verifying invalid title");
        });
        IllegalArgumentException blankException = assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse("  ", "TT02", "Verifying invalid title");
        });
        IllegalArgumentException nullExceptionObject = assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse(new Course(null, "TT02", "Verifying invalid title"));
        });
        IllegalArgumentException blankExceptionObject = assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse(new Course("  ", "TT02", "Verifying invalid title"));
        });

        assertAll(() -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, nullException.getMessage()),
                () -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, blankException.getMessage()),
                () -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, nullExceptionObject.getMessage()),
                () -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, blankExceptionObject.getMessage()));
    }

    @Test
    public void givenInvalidCourseWithInvalidCode_whenCreated_thenExceptionIsThrown() {
        // given

        // when & then
        IllegalArgumentException nullException = assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse("Test Course", null, "Verifying invalid code");
        });
        IllegalArgumentException blankException = assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse("Test Course", "  ", "Verifying invalid code");
        });
        IllegalArgumentException nullExceptionObject = assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse(new Course("Test Course", null, "Verifying invalid code"));
        });
        IllegalArgumentException blankExceptionObject = assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse(new Course("Test Course", "  ", "Verifying invalid code"));
        });

        assertAll(() -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, nullException.getMessage()),
                () -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, blankException.getMessage()),
                () -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, nullExceptionObject.getMessage()),
                () -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, blankExceptionObject.getMessage()));
    }

    @Test
    public void givenNullCoursePassedIn_whenCreated_thenExceptionIsThrown() {
        // given

        // when & then
        IllegalArgumentException nullException = assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse(null);
        });

        assertEquals(CourseService.INVALID_INPUT_MESSAGE, nullException.getMessage());
    }

    @Test
    public void givenExistingCourses_whenRetrievedByCode_thenValidCourseIsReturned() {
        // given
        var existingCourses = buildSetOfTestCourses();
        given(courseDAOMock.getAll()).willReturn(existingCourses);

        // when
        Course resultingCourse = courseService.getCourseByCode("C002");

        // then
        var expectedCourse = existingCourses.stream().filter(c -> c.getCode().equals("C002")).toList().getFirst();
        validateAllCourseProperties(expectedCourse, resultingCourse);
    }

    @Test
    public void givenExistingCourses_whenRetrievedByCodeNotFound_thenNullIsReturned() {
        // given
        var existingCourses = buildSetOfTestCourses();
        given(courseDAOMock.getAll()).willReturn(existingCourses);

        // when
        Course resultingCourse = courseService.getCourseByCode("C100");

        // then
        assertNull(resultingCourse);
    }

    @Test
    public void givenExistingCourses_whenRetrievedById_thenValidCourseIsReturned() {
        // given
        var testCourse = buildTestCourse(1234, "Test Course", "TT1234", "Verifying retrieve by ID");
        given(courseDAOMock.contains(1234)).willReturn(true);
        given(courseDAOMock.get(1234)).willReturn(testCourse);

        // when
        Course resultingCourse = courseService.getCourse(1234);

        // then
        validateAllCourseProperties(testCourse, resultingCourse);
    }

    @Test
    public void givenExistingCourses_whenRetrievedByIdNotFound_thenNullIsReturned() {
        // given
        given(courseDAOMock.contains(1234)).willReturn(false);

        // when
        Course resultingCourse = courseService.getCourse(1234);

        // then
        then(courseDAOMock).should(never()).get(1234);
        assertNull(resultingCourse);
    }

    @Test
    public void givenExistingCourses_whenAllRetrieved_thenExpectedListOfCoursesIsReturned() {
        // given
        var list = buildSetOfTestCourses();
        given(courseDAOMock.getAll()).willReturn(list);

        // when
        List<Course> results = courseService.getAllCourses();

        // then
        assertEquals(list.size(), results.size());
        for (var counter = 0; counter < list.size(); counter++) {
            assertEquals(list.get(counter), results.get(counter));
        }
    }

    @Test
    public void givenNoCourses_whenAllRetrieved_thenEmptyListIsReturned() {
        // given
        given(courseDAOMock.getAll()).willReturn(new ArrayList<>());

        // when
        List<Course> results = courseService.getAllCourses();

        // then
        assertEquals(0, results.size());
    }

    @Test
    public void givenInvalidCourseWithInvalidTitle_whenUpdated_thenExceptionIsThrown() {
        // given
        Course courseWithNullTitle = new Course(null, "00001", "description");
        Course courseWithBlankTitle = new Course("  ", "00002", "description");

        // when & then
        IllegalArgumentException nullException = assertThrows(IllegalArgumentException.class, () -> {
            courseService.updateCourse(courseWithNullTitle);
        });
        IllegalArgumentException blankException = assertThrows(IllegalArgumentException.class, () -> {
            courseService.updateCourse(courseWithBlankTitle);
        });

        assertAll(() -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, nullException.getMessage()),
                () -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, blankException.getMessage()));
    }

    @Test
    public void givenInvalidCourseWithInvalidCode_whenUpdated_thenExceptionIsThrown() {
        // given
        Course courseWithNullCode = new Course("Course 1", null, "description");
        Course courseWithBlankCode = new Course("Course 2", "  ", "description");

        // when & then
        IllegalArgumentException nullException = assertThrows(IllegalArgumentException.class, () -> {
            courseService.updateCourse(courseWithNullCode);
        });
        IllegalArgumentException blankException = assertThrows(IllegalArgumentException.class, () -> {
            courseService.updateCourse(courseWithBlankCode);
        });

        assertAll(() -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, nullException.getMessage()),
                () -> assertEquals(CourseService.INVALID_INPUT_MESSAGE, blankException.getMessage()));
    }

    @Test
    public void givenExistingCourse_whenUpdated_thenUpdateSucceeds() {
        // given
        var testCourse = buildTestCourse(42, "Test Course", "TT01", "Verifying update");
        given(courseDAOMock.contains(testCourse)).willReturn(true);

        // when
        courseService.updateCourse(testCourse);

        // then
        then(courseDAOMock).should().update(testCourse);
    }

    @Test
    public void givenCourseThatDoesNotExist_whenUpdated_thenNothingHappens() {
        // given
        var testCourse = buildTestCourse(42, "Test Course", "TT01", "Verifying update");
        given(courseDAOMock.contains(any(Course.class))).willReturn(false);

        // when
        courseService.updateCourse(testCourse);

        // then
        then(courseDAOMock).should(never()).update(testCourse);
    }

    @Test
    public void givenExistingCourse_whenDeleted_thenUpdateSucceeds() {
        // given
        var testCourse = buildTestCourse(42, "Test Course", "TT01", "Verifying update");
        given(courseDAOMock.contains(42)).willReturn(true);
        given(courseDAOMock.get(42)).willReturn(testCourse);

        // when
        courseService.deleteCourse(42);

        // then
        then(courseDAOMock).should().delete(testCourse);
    }

    @Test
    public void givenCourseThatDoesNotExist_whenDeleted_thenNothingHappens() {
        // given
        given(courseDAOMock.contains(anyInt())).willReturn(false);

        // when
        courseService.deleteCourse(42);

        // then
        then(courseDAOMock).should(never()).get(anyInt());
        then(courseDAOMock).should(never()).delete(any(Course.class));
    }

    private List<Course> buildSetOfTestCourses() {
        Course testCourse1 = buildTestCourse(1, "Course 1", "C001", "First test course");
        Course testCourse2 = buildTestCourse(2, "Course 2", "C002", "Second test course");
        Course testCourse3 = buildTestCourse(3, "Course 3", "C003", "Third test course");
        List<Course> testCourses = new ArrayList<>();
        testCourses.add(testCourse1);
        testCourses.add(testCourse2);
        testCourses.add(testCourse3);
        return testCourses;
    }

    private Course buildTestCourse(int id, String title, String code, String description) {
        Course testCourse = new Course(title, code, description);
        testCourse.setId(id);
        return testCourse;
    }
    
    private void validateAllCourseProperties(Course expectedCourse, Course actualCourse) {
        assertAll(() -> assertEquals(expectedCourse.getId(), actualCourse.getId()),
                () -> assertEquals(expectedCourse.getTitle(), actualCourse.getTitle()),
                () -> assertEquals(expectedCourse.getCode(), actualCourse.getCode()),
                () -> assertEquals(expectedCourse.getDescription(), actualCourse.getDescription()));
    }

}