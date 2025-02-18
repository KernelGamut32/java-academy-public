package gts.springBoot.registration.service;

import gts.springBoot.registration.dao.BaseDAO;
import gts.springBoot.registration.domain.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class CourseServiceTest {

    private Course course1;

    @InjectMocks
    private CourseService courseService;

    @Mock
    private BaseDAO<Course> courseDAO;

    @BeforeEach
    public void setUp() {
        course1 = new Course();
        course1.setId(1);
        course1.setTitle("Test Course");
        course1.setCode("T100");
        course1.setCredits(1.0);
    }

    @Test
    public void testFindAllCourses() {
        // Arrange
        Mockito.when(courseDAO.findAll()).thenReturn(List.of(course1));

        // Act
        List<Course> results = courseService.getAllCourses();

        // Assert
        assertAll(
                () -> assertNotNull(results),
                () -> assertEquals(1, results.size()),
                () -> assertEquals(course1, results.getFirst())
        );

        Mockito.verify(courseDAO, Mockito.times(1)).findAll();
    }

    @Test
    public void testCreateCourse() {
        // Arrange
        Mockito.when(courseDAO.insert(course1)).thenReturn(course1);
        Mockito.when(courseDAO.findById(1)).thenReturn(course1);

        // Act
        Course createdCourse = courseService.createCourse(course1);

        // Assert
        Course result = courseService.getCourse(createdCourse.getId());

        assertEquals(course1, result);

        Mockito.verify(courseDAO, Mockito.times(1)).insert(course1);
        Mockito.verify(courseDAO, Mockito.times(1)).findById(1);
    }

    @Test
    public void testDeleteCourse() {
        // Arrange
        Mockito.when(courseDAO.findById(1)).thenReturn(course1);
        Mockito.when(courseDAO.delete(course1)).thenReturn(true);

        // Act
        boolean result = courseService.deleteCourse(1);

        // Assert
        assertTrue(result);

        Mockito.verify(courseDAO, Mockito.times(1)).findById(1);
        Mockito.verify(courseDAO, Mockito.times(1)).delete(course1);
    }

    @Test
    public void testDeleteNonExistentCourse() {
        // Arrange
        Mockito.when(courseDAO.findById(9999)).thenReturn(null);

        // Act
        boolean result = courseService.deleteCourse(9999);

        // Assert
        assertFalse(result);

        Mockito.verify(courseDAO).findById(9999);
        Mockito.verify(courseDAO, never()).delete(any());
    }

    @Test
    public void testUpdateCourse() {
        // Arrange
        Mockito.when(courseDAO.findById(1)).thenReturn(course1);
        Mockito.when(courseDAO.update(course1)).thenReturn(true);

        // Act
        boolean result = courseService.updateCourse(course1);

        //Assert
        assertTrue(result);

        Mockito.verify(courseDAO, atMostOnce()).findById(1);
        Mockito.verify(courseDAO, atMostOnce()).update(course1);
    }

    @Test
    public void testUpdateNonExistentCourse() {
        // Arrange
        course1.setId(9999);

        Mockito.when(courseDAO.findById(9999)).thenReturn(null);

        // Act
        boolean result = courseService.updateCourse(course1);

        // Assert
        assertFalse(result);

        Mockito.verify(courseDAO).findById(9999);
        Mockito.verify(courseDAO, never()).update(any());
    }

    @Test
    public void testGetByCourseCode() {
        // Arrange
        List<Course> ls = List.of(course1);
        Mockito.when(courseDAO.findBy(any())).thenAnswer(inv -> ls);

        // Act
        List<Course> results = courseService.getCourseByCode("T100");

        // Assert
        assertAll(
                () -> assertNotNull(results),
                () -> assertEquals(1, results.size()),
                () -> assertEquals(course1, results.getFirst())
        );

        Mockito.verify(courseDAO).findBy(any());
    }

    @Test
    public void testGetByNonExistentCourseCode() {
        // Arrange
        Mockito.when(courseDAO.findBy(any())).thenAnswer(inv -> List.of());

        // Act
        List<Course> results = courseService.getCourseByCode("NOT THERE");

        // Assert
        assertAll(
                () -> assertNotNull(results),
                () -> assertEquals(0, results.size())
        );

        Mockito.verify(courseDAO).findBy(any());
    }
}