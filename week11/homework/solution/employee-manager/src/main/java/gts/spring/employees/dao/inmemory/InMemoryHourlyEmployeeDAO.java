package gts.spring.employees.dao.inmemory;

import gts.spring.employees.dao.BaseDAO;
import gts.spring.employees.domain.HourlyEmployee;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryHourlyEmployeeDAO implements BaseDAO<HourlyEmployee> {
    private Map<Long, HourlyEmployee> employees = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    @Override
    public boolean update(HourlyEmployee employee) {
        return employees.computeIfPresent(employee.getId(),
                (key, oldValue) -> employee) != null;
    }

    @Override
    public boolean delete(HourlyEmployee employee) {
        return employees.remove(employee.getId()) != null;
    }

    @Override
    public HourlyEmployee insert(HourlyEmployee employee) {
        Long newId = nextId.getAndIncrement();
        employee.setId(newId);
        employees.put(newId, employee);

        return employee;
    }

    @Override
    public Optional<HourlyEmployee> findById(Long id) {
        return Optional.ofNullable(employees.get(id));
    }

    @Override
    public List<HourlyEmployee> findAll() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public void resetDataStore() {
        employees = new ConcurrentHashMap<>();
        nextId = new AtomicLong(1);
    }
}
