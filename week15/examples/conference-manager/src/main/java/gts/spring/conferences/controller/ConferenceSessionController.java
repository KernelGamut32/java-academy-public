package gts.spring.conferences.controller;

import gts.spring.conferences.dto.ConferenceSessionDTO;
import gts.spring.conferences.service.ConferenceSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@Tag(name = "Conference Sessions", description = "Endpoints for managing Conference Sessions")
@RequiredArgsConstructor
public class ConferenceSessionController {

    private final ConferenceSessionService sessionService;

    @Operation(summary = "Get all conference sessions")
    @GetMapping
    public ResponseEntity<List<ConferenceSessionDTO>> getAllSessions() {
        return ResponseEntity.ok(sessionService.findAll());
    }

    @Operation(summary = "Get a conference session by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ConferenceSessionDTO> getSessionById(@PathVariable Long id) {
        var session = sessionService.findById(id);
        return session != null ? ResponseEntity.ok(session) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new conference session")
    @PostMapping
    public ResponseEntity<ConferenceSessionDTO> createSession(@Valid @RequestBody ConferenceSessionDTO sessionDTO) {
        return ResponseEntity.status(201).body(sessionService.create(sessionDTO));
    }

    @Operation(summary = "Update an existing conference session by ID (PUT)")
    @PutMapping("/{id}")
    public ResponseEntity<ConferenceSessionDTO> updateSession(@PathVariable Long id,
                                                              @Valid @RequestBody ConferenceSessionDTO sessionDTO) {
        var session = sessionService.findById(id);
        return session != null ? ResponseEntity.ok(sessionService.update(id, sessionDTO)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete an existing conference session by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Register a new attendee in an existing conference session")
    @PostMapping("/{sessionId}/attendees/{attendeeId}")
    public ResponseEntity<ConferenceSessionDTO> registerAttendee(@PathVariable Long sessionId, @PathVariable Long attendeeId) {
        var session = sessionService.registerAttendee(sessionId, attendeeId);
        return session != null ? ResponseEntity.ok(session)
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Assign a new presenter to an existing conference session")
    @PostMapping("/{sessionId}/presenters/{presenterId}")
    public ResponseEntity<ConferenceSessionDTO> assignPresenter(@PathVariable Long sessionId, @PathVariable Long presenterId) {
        var session = sessionService.assignPresenter(sessionId, presenterId);
        return session != null ? ResponseEntity.ok(session)
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get all conference sessions in which the given attendee is registered")
    @GetMapping("/attendee/{attendeeId}")
    public ResponseEntity<List<ConferenceSessionDTO>> getSessionsByAttendee(@PathVariable Long attendeeId) {
        return ResponseEntity.ok(sessionService.findByAttendeeId(attendeeId));
    }

    @Operation(summary = "Get all conference sessions to which the given presenter is assigned")
    @GetMapping("/presenter/{presenterId}")
    public ResponseEntity<List<ConferenceSessionDTO>> getSessionsByPresenter(@PathVariable Long presenterId) {
        return ResponseEntity.ok(sessionService.findByPresenterId(presenterId));
    }
}
