package gts.spring.registration.service;

import gts.spring.registration.dao.BaseDAO;
import gts.spring.registration.domain.Course;
import gts.spring.registration.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        course1 = Course.builder()
                    .title("Test Course")
                    .code("T100")
                    .credits(1.0)
                    .build();
        course1.setId(1L);
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
        Mockito.when(courseDAO.findById(1L)).thenReturn(Optional.ofNullable(course1));

        // Act
        Course createdCourse = courseService.createCourse(course1);

        // Assert
        Course result = courseService.getCourseById(createdCourse.getId());

        assertEquals(course1, result);

        Mockito.verify(courseDAO, Mockito.times(1)).insert(course1);
        Mockito.verify(courseDAO, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testDeleteCourse() {
        // Arrange
        Mockito.when(courseDAO.findById(1L)).thenReturn(Optional.ofNullable(course1));
        Mockito.when(courseDAO.delete(course1)).thenReturn(true);

        // Act
        boolean result = courseService.deleteCourse(1L);

        // Assert
        assertTrue(result);

        Mockito.verify(courseDAO, Mockito.times(1)).findById(1L);
        Mockito.verify(courseDAO, Mockito.times(1)).delete(course1);
    }

    @Test
    public void testDeleteNonExistentCourse() {
        // Arrange
        Mockito.when(courseDAO.findById(9999L)).thenReturn(Optional.empty());

        // Act
        boolean result = courseService.deleteCourse(9999L);

        // Assert
        assertFalse(result);

        Mockito.verify(courseDAO).findById(9999L);
        Mockito.verify(courseDAO, never()).delete(any());
    }

    @Test
    public void testUpdateCourse() {
        // Arrange
        Mockito.when(courseDAO.findById(1L)).thenReturn(Optional.ofNullable(course1));
        Mockito.when(courseDAO.update(course1)).thenReturn(true);

        // Act
        boolean result = courseService.updateCourse(course1);

        //Assert
        assertTrue(result);

        Mockito.verify(courseDAO, atMostOnce()).findById(1L);
        Mockito.verify(courseDAO, atMostOnce()).update(course1);
    }

    @Test
    public void testUpdateNonExistentCourse() {
        // Arrange
        course1.setId(9999L);

        Mockito.when(courseDAO.findById(9999L)).thenReturn(Optional.empty());

        // Act
        boolean result = courseService.updateCourse(course1);

        // Assert
        assertFalse(result);

        Mockito.verify(courseDAO).findById(9999L);
        Mockito.verify(courseDAO, never()).update(any());
    }

    @Test
    public void testGetByCourseCode() {
        // Arrange
        List<Course> ls = List.of(course1);
        Mockito.when(courseDAO.findBy(any())).thenAnswer(inv -> ls);

        // Act
        Course results = courseService.getCourseByCode("T100");

        // Assert
        assertAll(
                () -> assertNotNull(results),
                () -> assertEquals(course1.getId(), results.getId()),
                () -> assertEquals(course1.getTitle(), results.getTitle()),
                () -> assertEquals(course1.getCode(), results.getCode()),
                () -> assertEquals(course1.getCredits(), results.getCredits())
        );

        Mockito.verify(courseDAO).findBy(any());
    }

    @Test
    public void testGetByNonExistentCourseCode() {
        // Arrange
        Mockito.when(courseDAO.findBy(any())).thenAnswer(inv -> List.of());

        // Act
        List<Course> results = courseService.getCoursesByCode("NOT THERE");
        Course result = courseService.getCourseByCode("NOT THERE");

        // Assert
        assertEquals(0, results.size());
        assertNull(result);

        Mockito.verify(courseDAO, times(2)).findBy(any());
    }
}
