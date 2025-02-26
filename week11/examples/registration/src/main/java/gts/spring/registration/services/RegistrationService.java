package gts.spring.registration.services;

import gts.spring.registration.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gts.spring.registration.domain.ScheduledClass;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final StudentService studentService;
    private final ScheduledClassService scheduledClassService;

    public boolean deleteClassFromSchedule(String courseCode, LocalDate startDate, LocalDate endDate) {
        List<ScheduledClass> scheduledClasses = scheduledClassService.getScheduledClasses(courseCode, startDate, endDate);
        scheduledClasses.forEach(this::deleteClassFromSchedule);
        return !scheduledClasses.isEmpty();
    }

    private void deleteClassFromSchedule(ScheduledClass scheduledClass) {
        scheduledClass.getStudents()
                .forEach(s -> s.dropClass(scheduledClass));
        scheduledClassService.deleteScheduledClass(scheduledClass.getId());
    }

    public ScheduledClass addNewClassToSchedule(String courseCode, LocalDate startDate, LocalDate endDate) {
        return scheduledClassService.createScheduledClass(courseCode, startDate, endDate);
    }

    public boolean registerStudentForClass(Long studentId, String courseCode, LocalDate startDate) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            List<ScheduledClass> classes = scheduledClassService.getScheduledClassesByCourseCode(courseCode);
            for (ScheduledClass scheduledClass : classes) {
                if (scheduledClass.getStartDate().equals(startDate)) {
                    scheduledClass.addStudent(student);
                    student.addClass(scheduledClass);
                    scheduledClassService.updateScheduledClass(scheduledClass);
                    studentService.updateStudent(student);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dropStudentFromClass(Long studentId, String courseCode, LocalDate startDate) {
        Student student = studentService.getStudentById(studentId);
        List<ScheduledClass> classes = scheduledClassService.getScheduledClassesByCourseCode(courseCode);
        for (ScheduledClass scheduledClass : classes) {
            if (scheduledClass.getStartDate().equals(startDate)) {
                scheduledClass.removeStudent(student);
                student.dropClass(scheduledClass);
                scheduledClassService.updateScheduledClass(scheduledClass);
                studentService.updateStudent(student);
                return true;
            }
        }
        return false;
    }

    public List<Student> getStudentsForClass(String courseCode, LocalDate startDate) {
        List<ScheduledClass> classes = scheduledClassService.getScheduledClassesByCourseCode(courseCode);
        for (ScheduledClass scheduledClass : classes) {
            if (scheduledClass.getStartDate().equals(startDate)) {
                return scheduledClass.getStudents();
            }
        }
        return new ArrayList<>();
    }

    public List<ScheduledClass> getStudentSchedule(Long studentId) {
        Student student = studentService.getStudentById(studentId);
        return student.getClasses();
    }
}
