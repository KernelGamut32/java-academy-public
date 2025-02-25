package gts.spring.registration.controllers;

import gts.spring.registration.domain.ScheduledClass;
import gts.spring.registration.domain.Student;
import gts.spring.registration.services.RegistrationService;
import gts.spring.registration.services.ScheduledClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final ScheduledClassService scheduledClassService;

    @GetMapping
    public ResponseEntity<List<ScheduledClass>> getAllScheduledClasses() {
        return ResponseEntity.ok(scheduledClassService.getAllScheduledClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledClass> getScheduledClassById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduledClassService.getScheduledClassById(id));
    }

    @GetMapping("/code/{courseCode}")
    public ResponseEntity<List<ScheduledClass>> getScheduledClassesByCourseCode(@PathVariable String courseCode) {
        return ResponseEntity.ok(scheduledClassService.getScheduledClassesByCourseCode(courseCode));
    }

    @PostMapping
    public ResponseEntity<ScheduledClass> addScheduledClass(@RequestParam("code") String courseCode,
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                      @RequestParam("start") LocalDate startDate,
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                      @RequestParam("end") LocalDate endDate) {
        ScheduledClass scheduledClass = registrationService.addNewClassToSchedule(courseCode, startDate, endDate);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(scheduledClass.getId())
                .toUri();
        return ResponseEntity.created(uri).body(scheduledClass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduledClass> updateScheduledClass(@RequestBody ScheduledClass scheduledClass, @PathVariable Long id) {
        if (!Objects.equals(scheduledClass.getId(), id)) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = scheduledClassService.updateScheduledClass(scheduledClass);
        return result ? ResponseEntity.ok(scheduledClass) : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerStudent(@RequestParam Long studentId,
                                                   @RequestParam("classId") Long scheduledClassId) {
        ScheduledClass scheduledClass = scheduledClassService.getScheduledClassById(scheduledClassId);
        if (scheduledClass != null && registrationService.registerStudentForClass(studentId,
                scheduledClass.getCourse().getCode(),
                scheduledClass.getStartDate())) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register/{studentId}/{classId}")
    public ResponseEntity<Void> registerStudentWithPath(@PathVariable Long studentId,
                                                     @PathVariable("classId") Long scheduledClassId) {
        return registerStudent(studentId, scheduledClassId);
    }

    @PostMapping("/drop")
    public ResponseEntity<Void> dropStudent(@RequestParam Long studentId,
                                               @RequestParam("classId") Long scheduledClassId) {
        ScheduledClass scheduledClass = scheduledClassService.getScheduledClassById(scheduledClassId);
        if (scheduledClass != null && registrationService.dropStudentFromClass(studentId,
                scheduledClass.getCourse().getCode(),
                scheduledClass.getStartDate())) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getRegisteredStudents(@RequestParam("code") String courseCode,
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                               @RequestParam("start") LocalDate startDate) {
        return ResponseEntity.ok(registrationService.getStudentsForClass(courseCode, startDate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassFromSchedule(@PathVariable("id") Long scheduledClassId) {
        ScheduledClass scheduledClass = scheduledClassService.getScheduledClassById(scheduledClassId);
        if (scheduledClass != null && registrationService.deleteClassFromSchedule(
                scheduledClass.getCourse().getCode(),
                scheduledClass.getStartDate(),
                scheduledClass.getEndDate())) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<List<ScheduledClass>> getStudentSchedule(@PathVariable("id") Long studentId) {
        return ResponseEntity.ok(registrationService.getStudentSchedule(studentId));
    }
}
