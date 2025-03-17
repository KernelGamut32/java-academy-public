package gts.spring.games.services;

import gts.spring.games.domain.Review;
import gts.spring.games.repositories.mongo.GameReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameReviewService {
    private final GameReviewRepository gameReviewRepository;

    public List<Review> getAllReviews() {
        return gameReviewRepository.findAll();
    }

    public Optional<Review> getReviewById(String id) {
        return gameReviewRepository.findById(id);
    }

    @Transactional
    public Review saveReview(Review review) {
        return gameReviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(String id) {
        gameReviewRepository.deleteById(id);
    }
}
