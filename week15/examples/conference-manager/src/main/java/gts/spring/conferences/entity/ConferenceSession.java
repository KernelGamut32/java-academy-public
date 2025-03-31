package gts.spring.conferences.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conference_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ConferenceSession extends BaseEntity {

    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "conference_session_presenter",
            joinColumns = @JoinColumn(name = "conference_session_id"),
            inverseJoinColumns = @JoinColumn(name = "presenter_id")
    )
    private Set<Presenter> presenters = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "conference_session_attendee",
            joinColumns = @JoinColumn(name = "conference_session_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id")
    )
    private Set<Attendee> attendees = new HashSet<>();
}
