package expeditors.week05.solutions.employees.service;

import expeditors.week05.solutions.employees.dao.EmployeeDAO;
import expeditors.week05.solutions.employees.dao.inmemory.InMemoryHourlyEmployeeDAO;
import expeditors.week05.solutions.employees.domain.Employee;
import expeditors.week05.solutions.employees.domain.HourlyEmployee;

import java.util.List;

public class HourlyEmployeeService {
    private final EmployeeDAO hourlyEmployeeDAO;

    public HourlyEmployeeService() {
        hourlyEmployeeDAO = new InMemoryHourlyEmployeeDAO();
    }

    public void createEmployee(String name, String jobTitle, double hoursWorked, double hourlyPayRate) {
        var hourlyEmployee = new HourlyEmployee(name, jobTitle, hoursWorked, hourlyPayRate);
        hourlyEmployeeDAO.create(hourlyEmployee);
    }

    public List<Employee> getAllEmployees() {
        return hourlyEmployeeDAO.getAll();
    }
}
