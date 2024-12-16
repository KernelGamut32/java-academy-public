package expeditors.week05.solutions.employees.app;

import expeditors.week05.solutions.employees.domain.Employee;
import expeditors.week05.solutions.employees.service.HourlyEmployeeService;
import expeditors.week05.solutions.employees.service.SalariedEmployeeService;

import java.util.List;

public class EmployeeApp {
    private HourlyEmployeeService hourlyEmployeeService;
    private SalariedEmployeeService salariedEmployeeService;

    public static void main(String[] args) {
        EmployeeApp ea = new EmployeeApp();
        ea.init();
        ea.postRequestToAddAnHourlyEmployee();
        ea.postRequestToAddASalariedEmployee();
        ea.getRequestForAllEmployees();
    }

    private void postRequestToAddAnHourlyEmployee() {
        hourlyEmployeeService.createEmployee("Peter Cottontail", "Accountant II", 40.0, 25.00);
    }

    private void postRequestToAddASalariedEmployee() {
        salariedEmployeeService.createEmployee("Tony Stark", "CEO", 500_000.00);
    }

    private void getRequestForAllEmployees() {
        List<Employee> employees = hourlyEmployeeService.getAllEmployees();
        employees.addAll(salariedEmployeeService.getAllEmployees());
        System.out.printf("Here is the list of %d Employees:\n", employees.size());
        employees.forEach(System.out::println);
    }

    private void init() {
        hourlyEmployeeService = new HourlyEmployeeService();
        hourlyEmployeeService.createEmployee("Joe Schmoe", "Programmer III", 42.5, 19.99);
        hourlyEmployeeService.createEmployee("Bob Roberts", "Programmer IV", 39.5, 18.88);
        salariedEmployeeService = new SalariedEmployeeService();
        salariedEmployeeService.createEmployee("Melissa Testing", "Programmer V", 100_000.00);
        salariedEmployeeService.createEmployee("Chuck Wagon", "Programmer I", 47_500.00);
    }
}
