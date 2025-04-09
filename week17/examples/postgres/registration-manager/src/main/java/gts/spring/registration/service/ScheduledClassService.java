package gts.spring.registration.service;

import gts.spring.registration.dto.ScheduledClassDTO;
import gts.spring.registration.entity.Course;
import gts.spring.registration.entity.ScheduledClass;
import gts.spring.registration.entity.Student;
import gts.spring.registration.mapper.ScheduledClassMapper;
import gts.spring.registration.repository.CourseRepository;
import gts.spring.registration.repository.ScheduledClassRepository;
import gts.spring.registration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduledClassService extends CrudService<ScheduledClass, ScheduledClassDTO, ScheduledClassRepository, ScheduledClassMapper> {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ScheduledClassService(ScheduledClassRepository repository,
                                 ScheduledClassMapper mapper,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository) {
        super(repository, mapper);
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public ScheduledClassDTO create(ScheduledClassDTO dto) {
        return updateScheduledClassWithCourse(dto);
    }

    @Override
    @Transactional
    public ScheduledClassDTO update(Long id, ScheduledClassDTO dto) {
        return updateScheduledClassWithCourse(dto);
    }

    @Transactional
    public ScheduledClassDTO registerStudent(Long scheduledClassId, Long studentId) {
        ScheduledClass scheduledClass = getRepository().findById(scheduledClassId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (scheduledClass == null || student == null) return null;
        scheduledClass.getStudents().add(student);
        return getMapper().toDTO(getRepository().save(scheduledClass));
    }

    @Transactional
    public ScheduledClassDTO dropStudent(Long scheduledClassId, Long studentId) {
        ScheduledClass scheduledClass = getRepository().findById(scheduledClassId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (scheduledClass == null || student == null) return null;
        scheduledClass.getStudents().remove(student);
        return getMapper().toDTO(getRepository().save(scheduledClass));
    }

    private ScheduledClassDTO updateScheduledClassWithCourse(ScheduledClassDTO dto) {
        if (dto == null || dto.getCourse() == null || dto.getCourse().getId() == null) return null;
        Course course = courseRepository.findById(dto.getCourse().getId()).orElse(null);
        if (course == null) return null;
        ScheduledClass scheduledClass = getMapper().toEntity(dto);
        scheduledClass.setCourse(course);
        return getMapper().toDTO(getRepository().save(scheduledClass));
    }
}
