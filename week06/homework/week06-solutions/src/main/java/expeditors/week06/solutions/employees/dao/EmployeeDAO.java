package expeditors.week06.solutions.employees.dao;

import expeditors.week06.solutions.employees.domain.Employee;

import java.util.List;

public interface EmployeeDAO {

    void create(Employee employee);
    Employee getById(int id);
    List<Employee> getAll();
    void update(Employee employee);
    void delete(Employee employee);

}
