package expeditors.week05.solutions.employees.domain;

import java.text.NumberFormat;
import java.util.Locale;

public abstract class Employee {
    private int id;
    private final String name;
    private final String jobTitle;

    private final Locale locale = Locale.US;
    protected final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    public Employee(String name, String jobTitle) {
        this.name = name;
        this.jobTitle = jobTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public abstract double calculateWeeklyPay();

    @Override
    public String toString() {
        return String.format("%s (%s)", getName(), getJobTitle());
    }
}
