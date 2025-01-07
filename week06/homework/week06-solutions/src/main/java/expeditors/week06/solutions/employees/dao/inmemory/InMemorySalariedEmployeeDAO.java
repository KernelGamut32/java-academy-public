package expeditors.week06.solutions.employees.dao.inmemory;

import expeditors.week06.solutions.employees.dao.EmployeeDAO;
import expeditors.week06.solutions.employees.domain.Employee;
import expeditors.week06.solutions.employees.domain.SalariedEmployee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemorySalariedEmployeeDAO implements EmployeeDAO {

    private final Map<Integer, SalariedEmployee> salariedEmployees = new HashMap<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public void create(Employee employee) throws IllegalArgumentException {
        if (employee instanceof SalariedEmployee salariedEmployee) {
            int id = nextId.getAndIncrement();
            salariedEmployee.setId(id);
            salariedEmployees.put(id, salariedEmployee);
        } else {
            throw new IllegalArgumentException("Invalid value provided for salaried employee");
        }
    }

    @Override
    public Employee getById(int id) {
        return salariedEmployees.values().stream().filter(e -> e.getId() == id)
                .toList().getFirst();
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(salariedEmployees.values());
    }

    @Override
    public void update(Employee employee) throws IllegalArgumentException {
        if (employee instanceof SalariedEmployee salariedEmployee) {
            if (salariedEmployees.containsKey(salariedEmployee.getId())) {
                salariedEmployees.put(salariedEmployee.getId(), salariedEmployee);
            }
        } else {
            throw new IllegalArgumentException("Invalid value provided for salaried employee");
        }
    }

    @Override
    public void delete(Employee employee) throws IllegalArgumentException {
        if (employee instanceof SalariedEmployee salariedEmployee) {
            salariedEmployees.remove(salariedEmployee.getId());
        } else {
            throw new IllegalArgumentException("Invalid value provided for salaried employee");
        }
    }

}
