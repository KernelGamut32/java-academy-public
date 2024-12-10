package expeditors.week04.solutions.employees.service;

import expeditors.week04.solutions.employees.dao.inmemory.InMemoryEmployeeDAO;
import expeditors.week04.solutions.employees.domain.Employee;

import java.util.List;

public class EmployeeService {
    private final InMemoryEmployeeDAO employeeDAO;

    public EmployeeService() {
        employeeDAO = new InMemoryEmployeeDAO();
    }

    public void createEmployee(String name, String jobTitle, double yearlySalary) {
        var employee = new Employee(name, jobTitle, yearlySalary);
        createEmployee(employee);
    }

    public void createEmployee(Employee employee) {
        employeeDAO.create(employee);
    }

    public List<Employee> getEmployeesByName(String name) {
        return employeeDAO.getByName(name);
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAll();
    }

    public void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    public void deleteEmployee(int id) {
        Employee employee = employeeDAO.getById(id);
        if (employee != null) {
            employeeDAO.delete(employee);
        }
    }
}
