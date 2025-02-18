package gts.springBoot.registration.service;

import gts.springBoot.registration.dao.BaseDAO;
import gts.springBoot.registration.domain.Student;
import gts.springBoot.registration.domain.StudentCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final BaseDAO<Student> studentDAO;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public StudentService(BaseDAO<Student> studentDAO,
                          ApplicationEventPublisher publisher) {
        this.studentDAO = studentDAO;
        this.publisher = publisher;
    }

    public Student createStudent(Student student) {
        student = studentDAO.insert(student);

        System.out.println("Going to publish in " + Thread.currentThread());
        publisher.publishEvent(new StudentCreatedEvent(this, student));

        return student;
    }

    public boolean deleteStudent(int id) {
        Student student = studentDAO.findById(id);
        if (student != null) {
            return studentDAO.delete(student);
        }
        return false;
    }

    public boolean updateStudent(Student newStudent) {
        Student oldStudent = studentDAO.findById(newStudent.getId());
        if (oldStudent != null) {
            return studentDAO.update(newStudent);
        }
        return false;
    }

    public Student getStudent(int id) {
        return studentDAO.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    public List<Student> getByNameAndPhoneNumber(String name, String phoneNumber) {
        return studentDAO.findBy(s -> s.getName().toLowerCase().contains(name.toLowerCase()) &&
                s.getPhoneNumber().toLowerCase().contains(phoneNumber.toLowerCase()));
    }
}
