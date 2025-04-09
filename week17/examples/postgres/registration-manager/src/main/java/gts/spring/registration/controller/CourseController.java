package gts.spring.registration.controller;

import gts.spring.registration.dto.CourseDTO;
import gts.spring.registration.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Endpoints for managing Courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Get all courses")
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @Operation(summary = "Get a course by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
        var course = courseService.findById(id);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new course")
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.status(201).body(courseService.create(courseDTO));
    }

    @Operation(summary = "Update an existing course by ID (PUT)")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id,
                                                  @Valid @RequestBody CourseDTO courseDTO) {
        var course = courseService.findById(id);
        return course != null ? ResponseEntity.ok(courseService.update(id, courseDTO)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete an existing course by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
