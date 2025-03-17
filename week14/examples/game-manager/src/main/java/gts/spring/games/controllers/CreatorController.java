package gts.spring.games.controllers;

import gts.spring.games.domain.Creator;
import gts.spring.games.services.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/creators")
public class CreatorController {
    private final CreatorService creatorService;

    @GetMapping
    public ResponseEntity<List<Creator>> getAllCreators() {
        return ResponseEntity.ok(creatorService.getAllCreators());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Creator> getCreatorById(@PathVariable Long id) {
        return creatorService.getCreatorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Creator> createCreator(@RequestBody Creator creator) {
        return ResponseEntity.ok(creatorService.saveCreator(creator));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Creator> updateCreator(@RequestBody Creator creator, @PathVariable Long id) {
        if (!Objects.equals(creator.getId(), id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(creatorService.saveCreator(creator));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreator(@PathVariable Long id) {
        creatorService.deleteCreator(id);
        return ResponseEntity.noContent().build();
    }
}
