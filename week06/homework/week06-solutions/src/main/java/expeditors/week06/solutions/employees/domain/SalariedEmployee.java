package expeditors.week06.solutions.employees.domain;

public class SalariedEmployee extends Employee {

    private final double yearlySalary;

    public SalariedEmployee(String name, String jobTitle, double yearlySalary) {
        super(name, jobTitle);
        this.yearlySalary = yearlySalary;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    @Override
    public double calculateWeeklyPay() {
        return getYearlySalary() / 52;
    }

    @Override
    public String toString() {
        return String.format("""
                Salaried Employee: %s
                Weekly Pay = %s
                Yearly Pay = %s
                """, super.toString(),
                currencyFormatter.format(calculateWeeklyPay()),
                currencyFormatter.format(getYearlySalary()));
    }

}
