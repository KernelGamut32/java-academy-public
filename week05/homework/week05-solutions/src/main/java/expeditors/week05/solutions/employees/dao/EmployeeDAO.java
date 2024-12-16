package expeditors.week05.solutions.employees.dao;

import expeditors.week05.solutions.employees.domain.Employee;

import java.util.List;

public interface EmployeeDAO {
    void create(Employee employee);
    Employee getById(int id);
    List<Employee> getAll();
    List<Employee> getByName(String name);
    void update(Employee employee);
    void delete(Employee employee);
}
