package gts.spring.registration.controller;

import gts.spring.registration.dto.CondensedClassDTO;
import gts.spring.registration.dto.ScheduledClassDTO;
import gts.spring.registration.service.ScheduledClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@Tag(name = "Scheduled Classes", description = "Endpoints for managing Scheduled Classes")
@RequiredArgsConstructor
@CrossOrigin(maxAge =  3600)
public class ScheduledClassController {

    private final ScheduledClassService scheduledClassService;

    @Operation(summary = "Get all scheduled classes")
    @GetMapping
    public ResponseEntity<List<ScheduledClassDTO>> getAllClasses() {
        return ResponseEntity.ok(scheduledClassService.findAll());
    }

    @Operation(summary = "Get a scheduled class by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ScheduledClassDTO> getClass(@PathVariable Long id) {
        var scheduledClass = scheduledClassService.findById(id);
        return scheduledClass != null ? ResponseEntity.ok(scheduledClass) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get condensed class details by ID")
    @GetMapping("/condensed/{id}")
    public ResponseEntity<CondensedClassDTO> getClassDetail(@PathVariable Long id) {
        var condensedClassDetail = scheduledClassService.retrieveCondensedClassDetail(id);
        return condensedClassDetail != null ? ResponseEntity.ok(condensedClassDetail) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new scheduled class")
    @PostMapping
    public ResponseEntity<ScheduledClassDTO> createClass(@Valid @RequestBody ScheduledClassDTO scheduledClassDTO) {
        ScheduledClassDTO scheduledClass = scheduledClassService.create(scheduledClassDTO);
        return scheduledClass != null ?
                ResponseEntity.status(201).body(scheduledClass) :
                ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Update an existing scheduled class by ID (PUT)")
    @PutMapping("/{id}")
    public ResponseEntity<ScheduledClassDTO> updateClass(@PathVariable Long id,
                                                         @Valid @RequestBody ScheduledClassDTO scheduledClassDTO) {
        var scheduledClass = scheduledClassService.findById(id);
        return scheduledClass != null ? ResponseEntity.ok(scheduledClassService.update(id, scheduledClassDTO)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete an existing scheduled class by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        scheduledClassService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Register a new student in an existing scheduled class")
    @PostMapping("/{scheduledClassId}/register/{studentId}")
    public ResponseEntity<ScheduledClassDTO> registerStudent(@PathVariable Long scheduledClassId, @PathVariable Long studentId) {
        var scheduledClass = scheduledClassService.registerStudent(scheduledClassId, studentId);
        return scheduledClass != null ? ResponseEntity.ok(scheduledClass) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Drop a student from an existing scheduled class")
    @PostMapping("/{scheduledClassId}/drop/{studentId}")
    public ResponseEntity<ScheduledClassDTO> dropStudent(@PathVariable Long scheduledClassId, @PathVariable Long studentId) {
        var scheduledClass = scheduledClassService.dropStudent(scheduledClassId, studentId);
        return scheduledClass != null ? ResponseEntity.ok(scheduledClass) : ResponseEntity.notFound().build();
    }
}
