package gts.springBoot.registration.controller;

import gts.springBoot.registration.domain.Course;
import gts.springBoot.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/courses")
public class CourseController {

    private final RegistrationService registrationService;

    @Autowired
    public CourseController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
//    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(registrationService.getCourseService().getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        return ResponseEntity.ok(registrationService.getCourseService().getCourse(id));
    }

    @GetMapping("/query/{code}")
    public ResponseEntity<List<Course>> getCoursesByCode(@PathVariable String code) {
        return ResponseEntity.ok(registrationService.getCourseService().getCourseByCode(code));
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        return ResponseEntity.ok(registrationService.getCourseService().createCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable int id) {
        if (course.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = registrationService.getCourseService().updateCourse(course);
        return result ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable int id) {
        boolean result = registrationService.getCourseService().deleteCourse(id);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
