package gts.spring.games.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = VideoGame.class
)
public class VideoGame extends BaseEntity {
    private String title;
    private String description;
    private String rating;

    @ManyToMany(mappedBy = "games", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Creator> creators = new ArrayList<>();

    public void removeCreator(Creator creator) {
        this.creators.remove(creator);
        creator.getGames().remove(this);
    }
}

