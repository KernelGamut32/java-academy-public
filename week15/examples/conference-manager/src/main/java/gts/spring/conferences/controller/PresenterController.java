package gts.spring.conferences.controller;

import gts.spring.conferences.dto.PresenterDTO;
import gts.spring.conferences.service.PresenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presenters")
@Tag(name = "Presenters", description = "Endpoints for managing Presenters")
@RequiredArgsConstructor
public class PresenterController {

    private final PresenterService presenterService;

    @Operation(summary = "Get all presenters")
    @GetMapping
    public ResponseEntity<List<PresenterDTO>> getAllPresenters() {
        return ResponseEntity.ok(presenterService.findAll());
    }

    @Operation(summary = "Get a presenter by ID")
    @GetMapping("/{id}")
    public ResponseEntity<PresenterDTO> getPresenter(@PathVariable Long id) {
        var presenter = presenterService.findById(id);
        return presenter != null ? ResponseEntity.ok(presenterService.findById(id)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new presenter")
    @PostMapping
    public ResponseEntity<PresenterDTO> createPresenter(@Valid @RequestBody PresenterDTO presenterDTO) {
        return ResponseEntity.status(201).body(presenterService.create(presenterDTO));
    }

    @Operation(summary = "Update an existing presenter by ID (PUT)")
    @PutMapping("/{id}")
    public ResponseEntity<PresenterDTO> updatePresenter(@PathVariable Long id,
                                                        @Valid @RequestBody PresenterDTO presenterDTO) {
        var presenter = presenterService.findById(id);
        return presenter != null ? ResponseEntity.ok(presenterService.update(id, presenterDTO)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete an existing presenter by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePresenter(@PathVariable Long id) {
        presenterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
