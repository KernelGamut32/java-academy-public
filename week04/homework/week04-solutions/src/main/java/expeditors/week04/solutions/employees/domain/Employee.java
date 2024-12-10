package expeditors.week04.solutions.employees.domain;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private String jobTitle;
    private double yearlySalary;

    private final Locale locale = Locale.US;
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    public Employee() {
        super();
    }

    public Employee(String name, String jobTitle, double yearlySalary) {
        super();
        this.name = name;
        this.jobTitle = jobTitle;
        this.yearlySalary = yearlySalary;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public double getWeeklySalary() {
        return yearlySalary / 52;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - Weekly Salary: %s, Yearly Salary: %s",
                getName(), getJobTitle(), currencyFormatter.format(getWeeklySalary()),
                currencyFormatter.format(getYearlySalary()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(name, employee.name) && Objects.equals(jobTitle, employee.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, jobTitle);
    }
}
