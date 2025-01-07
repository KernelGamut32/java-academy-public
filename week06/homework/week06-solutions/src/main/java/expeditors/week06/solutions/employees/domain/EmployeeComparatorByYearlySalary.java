package expeditors.week06.solutions.employees.domain;

import java.util.Comparator;

public class EmployeeComparatorByYearlySalary implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(extractYearlySalary(o1), extractYearlySalary(o2));
    }

    private double extractYearlySalary(Employee employee) {
        if (employee instanceof HourlyEmployee he) {
            return he.calculateYearlyPay();
        } else if (employee instanceof SalariedEmployee se) {
            return se.getYearlySalary();
        } else {
            return 0;
        }
    }

}
