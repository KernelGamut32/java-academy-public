package gts.spring.registration.service;

import gts.spring.registration.dto.StudentDTO;
import gts.spring.registration.entity.ScheduledClass;
import gts.spring.registration.entity.Student;
import gts.spring.registration.mapper.StudentMapper;
import gts.spring.registration.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService extends CrudService<Student, StudentDTO, StudentRepository, StudentMapper> {

    public StudentService(StudentRepository repository, StudentMapper mapper) {
        super(repository, mapper);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Student student = getRepository().findById(id).orElse(null);
        if (student == null) return;

        for (ScheduledClass scheduledClass : student.getScheduledClasses()) {
            scheduledClass.getStudents().remove(student);
        }

        student.getScheduledClasses().clear();
        super.delete(id);
    }
}
