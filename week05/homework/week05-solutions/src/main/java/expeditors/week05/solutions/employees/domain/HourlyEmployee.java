package expeditors.week05.solutions.employees.domain;

public class HourlyEmployee extends Employee {
    private final double hoursWorked;
    private final double hourlyPayRate;

    public HourlyEmployee(String name, String jobTitle, double hoursWorked, double hourlyPayRate) {
        super(name, jobTitle);
        this.hoursWorked = hoursWorked;
        this.hourlyPayRate = hourlyPayRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyPayRate() {
        return hourlyPayRate;
    }

    @Override
    public double calculateWeeklyPay() {
        return getHoursWorked() * getHourlyPayRate();
    }

    public double calculateYearlyPay() {
        return calculateWeeklyPay() * 52;
    }

    @Override
    public String toString() {
        return String.format("""
                Hourly Employee: %s
                Hours Worked = %.2f
                Hourly Pay Rate = %s
                Weekly Pay = %s
                Yearly Pay = %s
                """, super.toString(),
                getHoursWorked(),
                currencyFormatter.format(getHourlyPayRate()),
                currencyFormatter.format(calculateWeeklyPay()),
                currencyFormatter.format(calculateYearlyPay()));
    }
}
