package expeditors.week06.solutions.employees.domain;

import java.util.Comparator;

public class EmployeeComparatorByWeeklySalaryDescending implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return -1 * Double.compare(o1.calculateWeeklyPay(), o2.calculateWeeklyPay());
    }
}
