package gts.springBoot.registration.controller;

import gts.springBoot.registration.domain.Student;
import gts.springBoot.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/students")
public class StudentController {

    private final RegistrationService registrationService;

    @Autowired
    public StudentController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(registrationService.getStudentService().getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return ResponseEntity.ok(registrationService.getStudentService().getStudent(id));
    }

    @GetMapping("/query")
    public ResponseEntity<List<Student>> getStudentsByNameAndPhoneNUmber(@RequestParam String name, @RequestParam String phoneNumber) {
        return ResponseEntity.ok(registrationService.getStudentService().getByNameAndPhoneNumber(name, phoneNumber));
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(registrationService.getStudentService().createStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        if (student.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = registrationService.getStudentService().updateStudent(student);
        return result ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        boolean result = registrationService.getStudentService().deleteStudent(id);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
