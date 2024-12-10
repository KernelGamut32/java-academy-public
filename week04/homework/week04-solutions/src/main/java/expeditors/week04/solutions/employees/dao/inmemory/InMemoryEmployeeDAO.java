package expeditors.week04.solutions.employees.dao.inmemory;

import expeditors.week04.solutions.employees.domain.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryEmployeeDAO {
    private final Map<Integer, Employee> employees = new HashMap<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    public void create(Employee employee) {
        int id = nextId.getAndIncrement();
        employee.setId(id);
        employees.put(id, employee);
    }

    public Employee getById(int id) {
        return employees.get(id);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    public List<Employee> getByName(String name) {
        return employees.values().stream().
                filter(e -> e.getName().equals(name)).collect(Collectors.toList());
    }

    public void update(Employee employee) {
        if (employees.containsKey(employee.getId())) {
            employees.put(employee.getId(), employee);
        }
    }

    public void delete(Employee employee) {
        employees.remove(employee.getId());
    }
}
