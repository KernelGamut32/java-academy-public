package gts.springBoot.registration;

import gts.springBoot.registration.domain.StudentCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StudentCreatedHandler implements ApplicationListener<StudentCreatedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(StudentCreatedHandler.class);
    @Override
    public void onApplicationEvent(StudentCreatedEvent event) {
        logger.info("Student created with the following details: {}", event.getStudent());
    }
}
