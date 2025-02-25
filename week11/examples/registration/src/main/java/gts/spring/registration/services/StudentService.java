package gts.spring.registration.services;

import gts.spring.registration.dao.BaseDAO;
import gts.spring.registration.domain.Student;
import gts.spring.registration.domain.StudentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final BaseDAO<Student> studentDAO;
    private final ApplicationEventPublisher publisher;

    public Student createStudent(Student student) {
        student = studentDAO.insert(student);
        publisher.publishEvent(new StudentCreatedEvent(this, student));
        return student;
    }

    public boolean deleteStudent(Long id) {
        Optional<Student> student = studentDAO.findById(id);
        return student.filter(studentDAO::delete).isPresent();
    }

    public boolean updateStudent(Student student) {
        return studentDAO.findById(student.getId())
                .map(s -> {
                    s.setName(student.getName());
                    s.setPhoneNumber(student.getPhoneNumber());
                    s.setDob(student.getDob());
                    s.setStatus(student.getStatus());
                    return studentDAO.update(s);
                })
                .orElse(false);
    }

    public List<Student> getStudentsByNameAndPhoneNumber(String name, String phoneNumber) {
        return studentDAO.findBy(s -> s.getName().toLowerCase().contains(name.toLowerCase()) &&
                s.getPhoneNumber().toLowerCase().contains(phoneNumber.toLowerCase()));
    }

    public Student getStudentById(Long id) {
        return studentDAO.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }
}
