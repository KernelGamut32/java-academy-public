package gts.spring.registration.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StudentCreatedEvent extends ApplicationEvent {

    private final Student student;

    public StudentCreatedEvent(Object source, Student student) {
        super(source);
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentCreatedEvent{" +
                "student=" + getStudent() +
                "}";
    }
}
