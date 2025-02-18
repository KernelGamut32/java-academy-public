package gts.spring.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigDemoApplication {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context =
					 new AnnotationConfigApplicationContext(StandardHealthCareConfig.class, SpecialistHealthCareConfig.class)) {
			var localHealthCareOffice = context.getBean(LocalHealthCareOffice.class);
			var specialistHealthCareOffice = context.getBean(SpecialistHealthCareOffice.class);

			localHealthCareOffice.provideCare();
			specialistHealthCareOffice.provideCare();
		}
	}
}
