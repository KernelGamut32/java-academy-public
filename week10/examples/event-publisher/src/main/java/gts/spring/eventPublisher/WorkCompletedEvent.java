package gts.spring.eventPublisher;

import org.springframework.context.ApplicationEvent;

public class WorkCompletedEvent extends ApplicationEvent {

    private final String results;

    public WorkCompletedEvent(Object source, String results) {
        super(source);
        this.results = results;
    }

    public String getResults() {
        return results;
    }
}
