package gts.spring.games.controllers;

import gts.spring.games.domain.Review;
import gts.spring.games.services.GameReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class GameReviewController {
    private final GameReviewService gameReviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(gameReviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        return gameReviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return ResponseEntity.ok(gameReviewService.saveReview(review));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@RequestBody Review review, @PathVariable String id) {
        if (!Objects.equals(review.getId(), id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(gameReviewService.saveReview(review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        gameReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
