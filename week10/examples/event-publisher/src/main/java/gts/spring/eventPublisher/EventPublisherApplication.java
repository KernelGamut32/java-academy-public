package gts.spring.eventPublisher;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventPublisherApplication {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context =
					 new AnnotationConfigApplicationContext(WidgetWorkerCfg.class)) {
			WidgetWorker widgetWorker = (WidgetWorker) context.getBean("widgetWorker");
			Widget widget = widgetWorker.makeWidget("Thing-a-ma-Bob", "Just your run-of-the-mill widget",
					2, 3, 4);
			System.out.printf("Widget completed: %s%n", widget);
		}
	}
}
