package gts.spring.games.domain;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
@Data
public class Review {
    @Id
    private String id;
    private Long videoGameId;
    private String reviewerName;
    private String comment;
    private double gameRating;
}
