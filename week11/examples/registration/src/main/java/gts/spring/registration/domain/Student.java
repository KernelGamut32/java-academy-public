package gts.spring.registration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class Student extends BaseEntity {

    public enum Status {
        FULL_TIME,
        PART_TIME,
        HIBERNATING
    }

    private String name;
    private String phoneNumber;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dob;
    private Status status;
    @ToString.Exclude
    @Singular
    @JsonIgnore
    private final List<ScheduledClass> classes = new ArrayList<>();

    public void addClass(ScheduledClass scheduledClass) {
        classes.add(scheduledClass);
    }

    public void dropClass(ScheduledClass scheduledClass) {
        classes.remove(scheduledClass);
    }
}
