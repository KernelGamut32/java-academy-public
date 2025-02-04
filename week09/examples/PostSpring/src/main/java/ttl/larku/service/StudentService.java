package ttl.larku.service;

import org.springframework.beans.factory.annotation.Autowired;
import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Student;

import java.time.LocalDate;
import java.util.List;

public class StudentService {

    private BaseDAO<Student> studentDAO;

    public StudentService() {
    }

    public Student createStudent(String name, String phoneNumber, Student.Status status) {
        Student student = new Student(name, phoneNumber, status);
        student = createStudent(student);

        return student;
    }

    public Student createStudent(String name, String phoneNumber, LocalDate dob, Student.Status status) {
        Student student = new Student(name, phoneNumber, dob, status);
        student = createStudent(student);

        return student;
    }

    public Student createStudent(Student student) {
        student = studentDAO.insert(student);

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
        if(oldStudent != null) {
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

    public BaseDAO<Student> getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(BaseDAO<Student> studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void clear() {
        studentDAO.deleteStore();
        studentDAO.createStore();
    }
}
