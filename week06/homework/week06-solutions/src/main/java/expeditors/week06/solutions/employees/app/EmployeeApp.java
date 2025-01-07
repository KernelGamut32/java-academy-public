package expeditors.week06.solutions.employees.app;

import expeditors.week06.solutions.employees.domain.Employee;
import expeditors.week06.solutions.employees.domain.EmployeeComparatorByWeeklySalaryDescending;
import expeditors.week06.solutions.employees.domain.EmployeeComparatorByYearlySalary;
import expeditors.week06.solutions.employees.domain.SummarizedEmployee;
import expeditors.week06.solutions.employees.service.HourlyEmployeeService;
import expeditors.week06.solutions.employees.service.SalariedEmployeeService;

import java.util.ArrayList;
import java.util.Collection;
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
        List<Employee> hourlyEmployees = hourlyEmployeeService.getAllEmployees();
        List<Employee> salariedEmployees = salariedEmployeeService.getAllEmployees();
        List<Employee> employees = new ArrayList<>();
        employees.addAll(hourlyEmployees);
        employees.addAll(salariedEmployees);

        System.out.printf("Here is the list of %d Employees:\n", employees.size());
        employees.forEach(System.out::println);

        System.out.println("Demonstrating operations on employees:");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Filter by specific name...");
        System.out.println("--------------------------------------------------------------------");
        filterEmployeesByName(employees, "Chuck Wagon").forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Filter by name containing characters...");
        System.out.println("--------------------------------------------------------------------");
        filterEmployeesNameByContains(employees, "Bo").forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("List employees in order of yearly salary (ascending)...");
        System.out.println("--------------------------------------------------------------------");
        employees.stream().sorted(new EmployeeComparatorByYearlySalary()).forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("List employees in order of weekly salary (descending)...");
        System.out.println("--------------------------------------------------------------------");
        employees.stream().sorted(new EmployeeComparatorByWeeklySalaryDescending()).forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Transform list of employees using map...");
        System.out.println("--------------------------------------------------------------------");
        employees.stream().map(e -> new SummarizedEmployee(e.getName(), e.getJobTitle())).
                forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Transform list of employees using flatMap...");
        System.out.println("See: https://howtodoinjava.com/java8/stream-map-vs-flatmap/");
        System.out.println("--------------------------------------------------------------------");
        List<List<Employee>> multipleEmployeeLists = new ArrayList<>();
        multipleEmployeeLists.add(hourlyEmployees);
        multipleEmployeeLists.add(salariedEmployees);
        multipleEmployeeLists.stream().flatMap(Collection::stream).
                sorted(new EmployeeComparatorByYearlySalary()).forEach(System.out::println);
    }

    private void init() {
        hourlyEmployeeService = new HourlyEmployeeService();
        hourlyEmployeeService.createEmployee("Joe Schmoe", "Programmer III", 42.5, 19.99);
        hourlyEmployeeService.createEmployee("Bob Roberts", "Programmer IV", 39.5, 18.88);
        hourlyEmployeeService.createEmployee("Lobo Jones", "Jester", 25.5, 11.00);
        salariedEmployeeService = new SalariedEmployeeService();
        salariedEmployeeService.createEmployee("Melissa Testing", "Programmer V", 100_000.00);
        salariedEmployeeService.createEmployee("Chuck Wagon", "Programmer I", 47_500.00);
        salariedEmployeeService.createEmployee("Chuck Wagon", "CEO", 147_500.00);
    }

    private List<Employee> filterEmployeesByName(List<Employee> employees, String searchName) {
        return employees.stream().filter(e -> e.getName().equals(searchName)).toList();
    }

    private List<Employee> filterEmployeesNameByContains(List<Employee> employees, String characters) {
        return employees.stream().filter(e -> e.getName().toLowerCase().
                contains(characters.toLowerCase())).toList();
    }

}
