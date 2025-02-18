package gts.spring.eventPublisher;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class WidgetWorker implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public Widget makeWidget(String name, String description,
                             int width, int length, int height) {
        var widget = new Widget();
        widget.setName(name);
        widget.setDescription(description);
        widget.setWidth(width);
        widget.setLength(length);
        widget.setHeight(height);
        ctx.publishEvent(new WorkCompletedEvent(this, widget.toString()));
        return widget;
    }
}
