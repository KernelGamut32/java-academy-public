package gts.spring.conferences.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "presenter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Presenter extends BaseEntity {

    private String name;
    private String bio;

    @ManyToMany(mappedBy = "presenters", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ConferenceSession> conferenceSessions = new HashSet<>();
}
