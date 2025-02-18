package gts.spring.eventPublisher;

import java.text.NumberFormat;
import java.util.Locale;

public class Widget {

    private static final Double COST_PER_CUBIC_UNIT = 2.99;

    private final Locale locale = Locale.US;
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    private String name;
    private String description;
    private Integer width;
    private Integer length;
    private Integer height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer calculateVolume() {
        return getWidth() * getLength() * getHeight();
    }

    public Double calculateCost() {
        return COST_PER_CUBIC_UNIT * calculateVolume();
    }

    @Override
    public String toString() {
        return String.format("%s (%s) with dimensions: %d x %d x %d and volume of %d - Total cost: %s",
                getName(), getDescription(), getWidth(), getLength(), getHeight(),
                calculateVolume(), currencyFormatter.format(calculateCost()));
    }
}
