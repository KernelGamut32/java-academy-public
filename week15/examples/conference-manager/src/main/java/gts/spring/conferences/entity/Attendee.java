package gts.spring.conferences.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attendee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Attendee extends BaseEntity {

    private String name;
    private String email;

    @ManyToMany(mappedBy = "attendees", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ConferenceSession> conferenceSessions = new HashSet<>();
}
