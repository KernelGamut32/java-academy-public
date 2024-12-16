package expeditors.week05.solutions.employees.service;

import expeditors.week05.solutions.employees.dao.EmployeeDAO;
import expeditors.week05.solutions.employees.dao.inmemory.InMemorySalariedEmployeeDAO;
import expeditors.week05.solutions.employees.domain.Employee;
import expeditors.week05.solutions.employees.domain.SalariedEmployee;

import java.util.List;

public class SalariedEmployeeService {
    private final EmployeeDAO salariedEmployeeDAO;

    public SalariedEmployeeService() {
        salariedEmployeeDAO = new InMemorySalariedEmployeeDAO();
    }

    public void createEmployee(String name, String jobTitle, double yearlySalary) {
        var salariedEmployee = new SalariedEmployee(name, jobTitle, yearlySalary);
        salariedEmployeeDAO.create(salariedEmployee);
    }

    public List<Employee> getAllEmployees() {
        return salariedEmployeeDAO.getAll();
    }
}
