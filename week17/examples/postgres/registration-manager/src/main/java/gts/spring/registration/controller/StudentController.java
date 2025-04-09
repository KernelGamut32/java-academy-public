package gts.spring.registration.controller;

import gts.spring.registration.dto.StudentDTO;
import gts.spring.registration.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Students", description = "Endpoints for managing Students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Get all students")
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @Operation(summary = "Get a student by ID")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable long id) {
        var student = studentService.findById(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new student")
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.status(201).body(studentService.create(studentDTO));
    }

    @Operation(summary = "Update an existing student by ID (PUT)")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable long id,
                                                    @Valid @RequestBody StudentDTO studentDTO) {
        var student = studentService.findById(id);
        return student != null ? ResponseEntity.ok(studentService.update(id, studentDTO)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete an existing student by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
