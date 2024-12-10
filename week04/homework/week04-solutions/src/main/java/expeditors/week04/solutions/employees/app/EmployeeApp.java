package expeditors.week04.solutions.employees.app;

import expeditors.week04.solutions.employees.domain.Employee;
import expeditors.week04.solutions.employees.service.EmployeeService;

import java.util.List;

public class EmployeeApp {
    private EmployeeService employeeService;

    public static void main(String[] args) {
        EmployeeApp ea = new EmployeeApp();
        ea.init();
        ea.postRequestToAddAnEmployee();
        ea.getRequestForAllEmployees();
    }

    private void postRequestToAddAnEmployee() {
        employeeService.createEmployee("Melissa Testing", "Accountant II", 76500.00);
    }

    private void getRequestForAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.printf("Here is the list of %d Employees:\n", employees.size());
        employees.forEach(System.out::println);
    }

    private void init() {
        employeeService = new EmployeeService();
        employeeService.createEmployee("Joe Schmoe", "Programmer III", 81500.00);
        employeeService.createEmployee("Bob Roberts", "Programmer IV", 91750.00);
        employeeService.createEmployee("Mary Contrary", "Architect II", 101500.00);
    }
}
