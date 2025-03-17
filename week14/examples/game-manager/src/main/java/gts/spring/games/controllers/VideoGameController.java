package gts.spring.games.controllers;

import gts.spring.games.domain.VideoGame;
import gts.spring.games.services.VideoGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class VideoGameController {
    private final VideoGameService gameService;

    @GetMapping
    public ResponseEntity<List<VideoGame>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllVideoGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoGame> getGameById(@PathVariable Long id) {
        return gameService.getVideoGameById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VideoGame> createGame(@RequestBody VideoGame game) {
        return ResponseEntity.ok(gameService.saveVideoGame(game));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoGame> updateGame(@RequestBody VideoGame game, @PathVariable Long id) {
        if (!Objects.equals(game.getId(), id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(gameService.saveVideoGame(game));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteVideoGame(id);
        return ResponseEntity.noContent().build();
    }
}
