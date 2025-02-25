package gts.spring.registration.controllers;

import gts.spring.registration.domain.Course;
import gts.spring.registration.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/query/{code}")
    public ResponseEntity<List<Course>> getCoursesByCode(@PathVariable String code) {
        return ResponseEntity.ok(courseService.getCoursesByCode(code));
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Long id) {
        if (!Objects.equals(course.getId(), id)) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = courseService.updateCourse(course);
        return result ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        boolean result = courseService.deleteCourse(id);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
