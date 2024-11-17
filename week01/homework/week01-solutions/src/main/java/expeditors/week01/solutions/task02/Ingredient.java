package expeditors.week01.solutions.task02;

public class Ingredient {
    private double amount;
    private String measureType;
    private String name;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%1.3f %s %s", amount, measureType, name);
    }
}
