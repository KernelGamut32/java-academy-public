package gts.spring.employees.services;

import gts.spring.employees.dao.jpa.HourlyEmployeeDAO;
import gts.spring.employees.domain.HourlyEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HourlyEmployeeService {

    private final HourlyEmployeeDAO hourlyEmployeeDAO;

    public HourlyEmployee createEmployee(HourlyEmployee employee) {
        return hourlyEmployeeDAO.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        hourlyEmployeeDAO.deleteById(id);
    }

    public HourlyEmployee updateEmployee(HourlyEmployee employee) {
        return hourlyEmployeeDAO.save(employee);
    }

    public HourlyEmployee getEmployeeById(Long id) {
        return hourlyEmployeeDAO.findById(id).orElse(null);
    }

    public Iterable<HourlyEmployee> getAllEmployees() {
        return hourlyEmployeeDAO.findAll();
    }
}
