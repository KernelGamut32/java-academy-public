package gts.spring.eventPublisher;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class WorkCompletedEventListener implements ApplicationListener<WorkCompletedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(WorkCompletedEventListener.class);

    @Override
    public void onApplicationEvent(@NotNull WorkCompletedEvent event) {
        logger.info("Received: {}", event.getResults());
    }
}
