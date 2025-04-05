package gts.spring.registration.service;

import gts.spring.registration.dto.CourseDTO;
import gts.spring.registration.entity.Course;
import gts.spring.registration.mapper.CourseMapper;
import gts.spring.registration.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfCourseDTOs() {
        Course course = new Course();
        CourseDTO courseDTO = CourseDTO.builder()
                .id(1L)
                .title("Introduction to Java")
                .code("A123")
                .credits(1.5)
                .build();
        List<Course> courses = List.of(course);
        List<CourseDTO> expected = List.of(courseDTO);

        when(courseRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(courses);
        when(courseMapper.toDTO(course)).thenReturn(courseDTO);

        List<CourseDTO> result = courseService.findAll();

        assertThat(result).isEqualTo(expected);
        verify(courseRepository).findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Test
    void findById_ShouldReturnCourseDTO_WhenFound() {
        Long id = 1L;
        Course course = new Course();
        CourseDTO courseDTO = CourseDTO.builder()
                .id(1L)
                .title("Introduction to Java")
                .code("A123")
                .credits(1.5)
                .build();

        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        when(courseMapper.toDTO(course)).thenReturn(courseDTO);

        CourseDTO result = courseService.findById(id);

        assertThat(result).isEqualTo(courseDTO);
    }

    @Test
    void findById_ShouldReturnNull_WhenNotFound() {
        Long id = 1L;

        when(courseRepository.findById(id)).thenReturn(Optional.empty());

        CourseDTO result = courseService.findById(id);

        assertThat(result).isNull();
    }

    @Test
    void create_ShouldSaveAndReturnCourseDTO() {
        Course course = new Course();
        CourseDTO courseDTO = CourseDTO.builder()
                .id(1L)
                .title("Introduction to Java")
                .code("A123")
                .credits(1.5)
                .build();

        when(courseMapper.toEntity(courseDTO)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.toDTO(course)).thenReturn(courseDTO);

        CourseDTO result = courseService.create(courseDTO);

        assertThat(result).isEqualTo(courseDTO);
        verify(courseRepository).save(course);
    }

    @Test
    void update_ShouldModifyAndReturnUpdatedCourseDTO_WhenFound() {
        Course course = new Course();
        CourseDTO courseDTO = CourseDTO.builder()
                .id(1L)
                .title("Introduction to Java")
                .code("A123")
                .credits(1.5)
                .build();

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.toDTO(course)).thenReturn(courseDTO);

        CourseDTO result = courseService.update(1L, courseDTO);

        verify(courseMapper).updateEntityFromDTO(courseDTO, course);
        assertThat(result).isEqualTo(courseDTO);
    }

    @Test
    void update_ShouldReturnNull_WhenCourseNotFound() {
        CourseDTO courseDTO = CourseDTO.builder().id(1L).build();

        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        CourseDTO result = courseService.update(1L, courseDTO);

        assertThat(result).isNull();
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        courseService.delete(1L);

        verify(courseRepository).deleteById(1L);
    }
}
