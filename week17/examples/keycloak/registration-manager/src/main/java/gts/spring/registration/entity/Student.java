package gts.spring.registration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Student extends BaseEntity {

    public enum Status {
        FULL_TIME,
        PART_TIME,
        HIBERNATING
    }

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Status status = Status.FULL_TIME;

    @ManyToMany(mappedBy = "students", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<ScheduledClass> scheduledClasses = new HashSet<>();
}
