package gts.spring.registration.controllers;

import gts.spring.registration.domain.Student;
import gts.spring.registration.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/query")
    public ResponseEntity<List<Student>> getStudentsByNameAndPhoneNUmber(@RequestParam String name, @RequestParam String phoneNumber) {
        return ResponseEntity.ok(studentService.getStudentsByNameAndPhoneNumber(name, phoneNumber));
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        if (!Objects.equals(student.getId(), id)) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = studentService.updateStudent(student);
        return result ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        boolean result = studentService.deleteStudent(id);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
