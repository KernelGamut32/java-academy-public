package gts.spring.registration.service;

import gts.spring.registration.dto.StudentDTO;
import gts.spring.registration.entity.ScheduledClass;
import gts.spring.registration.entity.Student;
import gts.spring.registration.mapper.StudentMapper;
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
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfStudentDTOs() {
        Student student = new Student();
        StudentDTO studentDTO = StudentDTO.builder()
                .id(1L)
                .firstName("First")
                .lastName("Last")
                .phoneNumber("111-111-1111")
                .dateOfBirth(Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .status("FULL_TIME")
                .build();
        List<Student> students = List.of(student);
        List<StudentDTO> expected = List.of(studentDTO);

        when(studentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(students);
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);

        List<StudentDTO> result = studentService.findAll();

        assertThat(result).isEqualTo(expected);
        verify(studentRepository).findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Test
    void findById_ShouldReturnStudentDTO_WhenFound() {
        Long id = 1L;
        Student student = new Student();
        StudentDTO studentDTO = StudentDTO.builder()
                .id(1L)
                .firstName("First")
                .lastName("Last")
                .phoneNumber("111-111-1111")
                .dateOfBirth(Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .status("FULL_TIME")
                .build();

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);

        StudentDTO result = studentService.findById(id);

        assertThat(result).isEqualTo(studentDTO);
    }

    @Test
    void findById_ShouldReturnNull_WhenNotFound() {
        Long id = 1L;

        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        StudentDTO result = studentService.findById(id);

        assertThat(result).isNull();
    }

    @Test
    void create_ShouldSaveAndReturnStudentDTO() {
        Student student = new Student();
        StudentDTO studentDTO = StudentDTO.builder()
                .id(1L)
                .firstName("First")
                .lastName("Last")
                .phoneNumber("111-111-1111")
                .dateOfBirth(Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .status("FULL_TIME")
                .build();

        when(studentMapper.toEntity(studentDTO)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);

        StudentDTO result = studentService.create(studentDTO);

        assertThat(result).isEqualTo(studentDTO);
        verify(studentRepository).save(student);
    }

    @Test
    void update_ShouldModifyAndReturnUpdatedStudentDTO_WhenFound() {
        Student student = new Student();
        StudentDTO studentDTO = StudentDTO.builder()
                .id(1L)
                .firstName("First")
                .lastName("Last")
                .phoneNumber("111-111-1111")
                .dateOfBirth(Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .status("FULL_TIME")
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);

        StudentDTO result = studentService.update(1L, studentDTO);

        verify(studentMapper).updateEntityFromDTO(studentDTO, student);
        assertThat(result).isEqualTo(studentDTO);
    }

    @Test
    void update_ShouldReturnNull_WhenStudentNotFound() {
        StudentDTO studentDTO = StudentDTO.builder().id(1L).build();

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        StudentDTO result = studentService.update(1L, studentDTO);

        assertThat(result).isNull();
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        Student student = Student.builder().id(1L).scheduledClasses(new HashSet<>()).build();
        ScheduledClass scheduledClass = ScheduledClass.builder()
                .id(1L)
                .students(new HashSet<>())
                .build();
        scheduledClass.getStudents().add(student);
        student.getScheduledClasses().add(scheduledClass);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.delete(1L);

        assertThat(student.getScheduledClasses()).isEmpty();
        verify(studentRepository).findById(1L);
        verify(studentRepository).deleteById(1L);
    }

    @Test
    void delete_ShouldNotDelete_WhenStudentNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        studentService.delete(1L);

        verify(studentRepository).findById(1L);
        verify(studentRepository, times(0)).deleteById(1L);
    }
}