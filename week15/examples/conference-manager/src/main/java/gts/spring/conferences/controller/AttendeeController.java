package gts.spring.conferences.controller;

import gts.spring.conferences.dto.AttendeeDTO;
import gts.spring.conferences.service.AttendeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendees")
@Tag(name = "Attendees", description = "Endpoints for managing Attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @Operation(summary = "Get all attendees")
    @GetMapping
    public ResponseEntity<List<AttendeeDTO>> getAllAttendees() {
        return ResponseEntity.ok(attendeeService.findAll());
    }

    @Operation(summary = "Get an attendee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AttendeeDTO> getAttendee(@PathVariable Long id) {
        var attendee = attendeeService.findById(id);
        return attendee != null ? ResponseEntity.ok(attendeeService.findById(id)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new attendee")
    @PostMapping
    public ResponseEntity<AttendeeDTO> createAttendee(@Valid @RequestBody AttendeeDTO attendeeDTO) {
        return ResponseEntity.status(201).body(attendeeService.create(attendeeDTO));
    }

    @Operation(summary = "Update an existing attendee by ID (PUT)")
    @PutMapping("/{id}")
    public ResponseEntity<AttendeeDTO> updateAttendee(@PathVariable Long id,
                                                      @Valid @RequestBody AttendeeDTO attendeeDTO) {
        var attendee = attendeeService.findById(id);
        return attendee != null ? ResponseEntity.ok(attendeeService.update(id, attendeeDTO)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete an existing attendee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendee(@PathVariable Long id) {
        attendeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
