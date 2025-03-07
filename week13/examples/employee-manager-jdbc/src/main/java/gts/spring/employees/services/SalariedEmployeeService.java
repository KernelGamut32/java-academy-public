package gts.spring.employees.services;

import gts.spring.employees.dao.BaseDAO;
import gts.spring.employees.domain.SalariedEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalariedEmployeeService {

    private final BaseDAO<SalariedEmployee> salariedEmployeeDAO;

    public SalariedEmployee createEmployee(SalariedEmployee employee) {
        return salariedEmployeeDAO.insert(employee);
    }

    public boolean deleteEmployee(Long id) {
        Optional<SalariedEmployee> employee = salariedEmployeeDAO.findById(id);
        return employee.filter(salariedEmployeeDAO::delete).isPresent();
    }

    public boolean updateEmployee(SalariedEmployee employee) {
        Optional<SalariedEmployee> existingEmployee = salariedEmployeeDAO.findById(employee.getId());
        return existingEmployee.isPresent() && existingEmployee
                .map(e -> {
                    e.setName(employee.getName());
                    e.setJobTitle(employee.getJobTitle());
                    e.setYearlySalary(employee.getYearlySalary());
                    return salariedEmployeeDAO.update(e);
                })
                .orElse(false);
    }

    public SalariedEmployee getEmployeeById(Long id) {
        return salariedEmployeeDAO.findById(id).orElse(null);
    }

    public List<SalariedEmployee> getAllEmployees() {
        return salariedEmployeeDAO.findAll();
    }
}
