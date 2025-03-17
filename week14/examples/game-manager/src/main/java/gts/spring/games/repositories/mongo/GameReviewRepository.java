package gts.spring.games.repositories.mongo;

import gts.spring.games.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameReviewRepository extends MongoRepository<Review, String> {
}
