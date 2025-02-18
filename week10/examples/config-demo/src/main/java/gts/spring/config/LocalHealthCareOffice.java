package gts.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LocalHealthCareOffice implements HealthCareOffice {

    private static final Logger logger = LoggerFactory.getLogger(LocalHealthCareOffice.class);
    private final StandardHealthCareProvider provider;

    @Autowired
    public LocalHealthCareOffice(@Qualifier("standardHealthCareProvider") StandardHealthCareProvider provider) {
        this.provider = provider;
    }

    @Override
    public void provideCare() {
        logger.info("Health care provider: {}", provider);
        logger.info("Providing care LOCALLY");
    }
}
