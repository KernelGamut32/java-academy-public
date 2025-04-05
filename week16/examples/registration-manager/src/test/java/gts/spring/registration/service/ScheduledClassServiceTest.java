package gts.spring.registration.service;

import gts.spring.registration.dto.CourseDTO;
import gts.spring.registration.dto.ScheduledClassDTO;
import gts.spring.registration.entity.Course;
import gts.spring.registration.entity.ScheduledClass;
import gts.spring.registration.entity.Student;
import gts.spring.registration.mapper.ScheduledClassMapper;
import gts.spring.registration.repository.CourseRepository;
import gts.spring.registration.repository.ScheduledClassRepository;
import gts.spring.registration.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.time.ZoneId;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ScheduledClassServiceTest {

    private final static Long SCHEDULED_CLASS_ID = 1L;
    private final static Long COURSE_ID = 2L;
    private final static Long STUDENT_ID = 3L;

    @Mock private ScheduledClassRepository scheduledClassRepository;
    @Mock private ScheduledClassMapper scheduledClassMapper;
    @Mock private StudentRepository studentRepository;
    @Mock private CourseRepository courseRepository;

    @InjectMocks
    private ScheduledClassService scheduledClassService;

    private ScheduledClassDTO scheduledClassDTO;
    private ScheduledClass scheduledClass;
    private Course course;
    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        scheduledClassDTO = buildTestScheduledClassDTO();
        scheduledClass = ScheduledClass.builder().id(SCHEDULED_CLASS_ID).build();
        course = Course.builder().id(COURSE_ID).build();
        student = Student.builder().id(STUDENT_ID).build();
    }

    @Test
    void findAll_ShouldReturnListOfScheduledClassDTOs() {
        List<ScheduledClass> scheduledClasses = List.of(scheduledClass);
        List<ScheduledClassDTO> expected = List.of(scheduledClassDTO);

        when(scheduledClassRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(scheduledClasses);
        when(scheduledClassMapper.toDTO(scheduledClass)).thenReturn(scheduledClassDTO);

        List<ScheduledClassDTO> result = scheduledClassService.findAll();

        assertThat(result).isEqualTo(expected);
        verify(scheduledClassRepository).findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Test
    void findById_ShouldReturnScheduledClassDTO_WhenFound() {
        when(scheduledClassRepository.findById(SCHEDULED_CLASS_ID)).thenReturn(Optional.of(scheduledClass));
        when(scheduledClassMapper.toDTO(scheduledClass)).thenReturn(scheduledClassDTO);

        ScheduledClassDTO result = scheduledClassService.findById(SCHEDULED_CLASS_ID);

        assertThat(result).isEqualTo(scheduledClassDTO);
    }

    @Test
    void findById_ShouldReturnNull_WhenNotFound() {
        when(scheduledClassRepository.findById(SCHEDULED_CLASS_ID)).thenReturn(Optional.empty());

        ScheduledClassDTO result = scheduledClassService.findById(SCHEDULED_CLASS_ID);

        assertThat(result).isNull();
    }

    @Test
    void create_ShouldSaveAndReturnScheduledClassDTO() {
        when(scheduledClassMapper.toEntity(scheduledClassDTO)).thenReturn(scheduledClass);
        when(scheduledClassRepository.save(scheduledClass)).thenReturn(scheduledClass);
        when(scheduledClassMapper.toDTO(scheduledClass)).thenReturn(scheduledClassDTO);
        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));

        ScheduledClassDTO result = scheduledClassService.create(scheduledClassDTO);

        assertThat(result).isEqualTo(scheduledClassDTO);
        verify(scheduledClassRepository).save(scheduledClass);
    }

    @Test
    void create_ShouldReturnNull_WhenNullDTOProvided() {
        assertThat(scheduledClassService.create(null)).isNull();
    }

    @Test
    void create_ShouldReturnNull_WhenNullCourseProvided() {
        scheduledClassDTO.setCourse(null);

        assertThat(scheduledClassService.create(scheduledClassDTO)).isNull();
    }

    @Test
    void create_ShouldReturnNull_WhenCourseWithNullIdProvided() {
        scheduledClassDTO.setCourse(CourseDTO.builder().id(null).build());

        assertThat(scheduledClassService.create(scheduledClassDTO)).isNull();
    }

    @Test
    void create_ShouldReturnNull_WhenProvidedCourseNotFound() {
        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.empty());

        ScheduledClassDTO result = scheduledClassService.create(scheduledClassDTO);

        assertThat(result).isNull();
    }

    @Test
    void update_ShouldModifyAndReturnUpdatedScheduledClassDTO_WhenFound() {
        when(scheduledClassRepository.findById(SCHEDULED_CLASS_ID)).thenReturn(Optional.of(scheduledClass));
        when(scheduledClassRepository.save(scheduledClass)).thenReturn(scheduledClass);
        when(scheduledClassMapper.toDTO(scheduledClass)).thenReturn(scheduledClassDTO);
        when(scheduledClassMapper.toEntity(scheduledClassDTO)).thenReturn(scheduledClass);
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));

        ScheduledClassDTO result = scheduledClassService.update(SCHEDULED_CLASS_ID, scheduledClassDTO);

        assertThat(result).isEqualTo(scheduledClassDTO);
    }

    @Test
    void updated_ShouldReturnNull_WhenNullDTOProvided() {
        assertThat(scheduledClassService.update(SCHEDULED_CLASS_ID,null)).isNull();
    }

    @Test
    void update_ShouldReturnNull_WhenNullCourseProvided() {
        scheduledClassDTO.setCourse(null);

        assertThat(scheduledClassService.update(SCHEDULED_CLASS_ID, scheduledClassDTO)).isNull();
    }

    @Test
    void update_ShouldReturnNull_WhenCourseWithNullIdProvided() {
        scheduledClassDTO.setCourse(CourseDTO.builder().id(null).build());

        assertThat(scheduledClassService.create(scheduledClassDTO)).isNull();
    }

    @Test
    void update_ShouldReturnNull_WhenProvidedCourseNotFound() {
        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.empty());

        ScheduledClassDTO result = scheduledClassService.update(SCHEDULED_CLASS_ID, scheduledClassDTO);

        assertThat(result).isNull();
    }

    @Test
    void update_ShouldReturnNull_WhenScheduledClassNotFound() {
        when(scheduledClassRepository.findById(SCHEDULED_CLASS_ID)).thenReturn(Optional.empty());

        ScheduledClassDTO result = scheduledClassService.update(SCHEDULED_CLASS_ID, scheduledClassDTO);

        assertThat(result).isNull();
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        scheduledClassService.delete(SCHEDULED_CLASS_ID);

        verify(scheduledClassRepository).deleteById(SCHEDULED_CLASS_ID);
    }

    @Test
    void registerStudent_ShouldAddStudent_WhenBothExist() {
        scheduledClass.setStudents(new HashSet<>());

        when(scheduledClassRepository.findById(SCHEDULED_CLASS_ID)).thenReturn(Optional.of(scheduledClass));
        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.of(student));
        when(scheduledClassRepository.save(scheduledClass)).thenReturn(scheduledClass);
        when(scheduledClassMapper.toDTO(scheduledClass)).thenReturn(scheduledClassDTO);

        ScheduledClassDTO result = scheduledClassService.registerStudent(SCHEDULED_CLASS_ID, STUDENT_ID);

        assertThat(scheduledClass.getStudents()).contains(student);
        assertThat(result).isEqualTo(scheduledClassDTO);
    }

    @Test
    void registerStudent_ShouldReturnNull_WhenClassOrStudentMissing() {
        when(scheduledClassRepository.findById(SCHEDULED_CLASS_ID)).thenReturn(Optional.empty());
        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.of(student));
        assertThat(scheduledClassService.registerStudent(SCHEDULED_CLASS_ID, STUDENT_ID)).isNull();

        when(scheduledClassRepository.findById(SCHEDULED_CLASS_ID)).thenReturn(Optional.of(scheduledClass));
        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.empty());
        assertThat(scheduledClassService.registerStudent(SCHEDULED_CLASS_ID, STUDENT_ID)).isNull();
    }

    @Test
    void dropStudent_ShouldDropStudent_WhenBothExist() {
        scheduledClass.setStudents(new HashSet<>(Collections.singletonList(student)));

        when(scheduledClassRepository.findById(SCHEDULED_CLASS_ID)).thenReturn(Optional.of(scheduledClass));
        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.of(student));
        when(scheduledClassRepository.save(scheduledClass)).thenReturn(scheduledClass);
        when(scheduledClassMapper.toDTO(scheduledClass)).thenReturn(scheduledClassDTO);

        ScheduledClassDTO result = scheduledClassService.dropStudent(SCHEDULED_CLASS_ID, STUDENT_ID);

        assertThat(scheduledClass.getStudents()).doesNotContain(student);
        assertThat(result).isEqualTo(scheduledClassDTO);
    }

    @Test
    void dropStudent_ShouldReturnNull_WhenClassOrStudentMissing() {
        when(scheduledClassRepository.findById(SCHEDULED_CLASS_ID)).thenReturn(Optional.empty());
        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.of(student));
        assertThat(scheduledClassService.dropStudent(SCHEDULED_CLASS_ID, STUDENT_ID)).isNull();

        when(scheduledClassRepository.findById(SCHEDULED_CLASS_ID)).thenReturn(Optional.of(scheduledClass));
        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.empty());
        assertThat(scheduledClassService.dropStudent(SCHEDULED_CLASS_ID, STUDENT_ID)).isNull();
    }

    private ScheduledClassDTO buildTestScheduledClassDTO() {
        return ScheduledClassDTO.builder()
                .id(SCHEDULED_CLASS_ID)
                .sectionName("S123")
                .startDate(Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .endDate(Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .course(CourseDTO.builder().id(COURSE_ID).build())
                .students(new ArrayList<>())
                .build();
    }
}
