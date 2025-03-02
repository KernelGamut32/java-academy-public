package gts.spring.employees.services;

import gts.spring.employees.dao.inmemory.InMemoryEmployeeDAO;
import gts.spring.employees.domain.HourlyEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HourlyEmployeeService {

    private final InMemoryEmployeeDAO<HourlyEmployee> hourlyEmployeeDAO;

    public HourlyEmployee createEmployee(HourlyEmployee employee) {
        return hourlyEmployeeDAO.insert(employee);
    }

    public boolean deleteEmployee(Long id) {
        Optional<HourlyEmployee> employee = hourlyEmployeeDAO.findById(id);
        return employee.filter(hourlyEmployeeDAO::delete).isPresent();
    }

    public boolean updateEmployee(HourlyEmployee employee) {
        Optional<HourlyEmployee> existingEmployee = hourlyEmployeeDAO.findById(employee.getId());
        return existingEmployee.isPresent() && existingEmployee
                .map(e -> {
                    e.setName(employee.getName());
                    e.setJobTitle(employee.getJobTitle());
                    e.setHourlyPayRate(employee.getHourlyPayRate());
                    e.setHoursWorked(employee.getHoursWorked());
                    return hourlyEmployeeDAO.update(e);
                })
                .orElse(false);
    }

    public HourlyEmployee getEmployeeById(Long id) {
        return hourlyEmployeeDAO.findById(id).orElse(null);
    }

    public List<HourlyEmployee> getAllEmployees() {
        return hourlyEmployeeDAO.findAll();
    }
}
