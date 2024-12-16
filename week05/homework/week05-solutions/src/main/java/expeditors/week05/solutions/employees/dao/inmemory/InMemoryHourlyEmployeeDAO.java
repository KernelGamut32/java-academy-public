package expeditors.week05.solutions.employees.dao.inmemory;

import expeditors.week05.solutions.employees.dao.EmployeeDAO;
import expeditors.week05.solutions.employees.domain.Employee;
import expeditors.week05.solutions.employees.domain.HourlyEmployee;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryHourlyEmployeeDAO implements EmployeeDAO {
    private final Map<Integer, HourlyEmployee> hourlyEmployees = new HashMap<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public void create(Employee employee) {
        if (employee instanceof HourlyEmployee hourlyEmployee) {
            int id = nextId.getAndIncrement();
            hourlyEmployee.setId(id);
            hourlyEmployees.put(id, hourlyEmployee);
        } else {
            throw new ArgumentAccessException("Invalid value provided for hourly employee");
        }
    }

    @Override
    public Employee getById(int id) {
        return hourlyEmployees.values().stream().filter(e -> e.getId() == id)
                .toList().getFirst();
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(hourlyEmployees.values());
    }

    @Override
    public List<Employee> getByName(String name) {
        return hourlyEmployees.values().stream().
                filter(e -> e.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public void update(Employee employee) {
        if (employee instanceof HourlyEmployee hourlyEmployee) {
            if (hourlyEmployees.containsKey(hourlyEmployee.getId())) {
                hourlyEmployees.put(hourlyEmployee.getId(), hourlyEmployee);
            }
        } else {
            throw new ArgumentAccessException("Invalid value provided for hourly employee");
        }
    }

    @Override
    public void delete(Employee employee) {
        if (employee instanceof HourlyEmployee hourlyEmployee) {
            hourlyEmployees.remove(hourlyEmployee.getId());
        } else {
            throw new ArgumentAccessException("Invalid value provided for hourly employee");
        }
    }
}
