package gts.spring.employees.services;

import gts.spring.employees.dao.jpa.SalariedEmployeeDAO;
import gts.spring.employees.domain.SalariedEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalariedEmployeeService {

    private final SalariedEmployeeDAO salariedEmployeeDAO;

    public SalariedEmployee createEmployee(SalariedEmployee employee) {
        return salariedEmployeeDAO.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        salariedEmployeeDAO.deleteById(id);
    }

    public SalariedEmployee updateEmployee(SalariedEmployee employee) {
        return salariedEmployeeDAO.save(employee);
    }

    public SalariedEmployee getEmployeeById(Long id) {
        return salariedEmployeeDAO.findById(id).orElse(null);
    }

    public Iterable<SalariedEmployee> getAllEmployees() {
        return salariedEmployeeDAO.findAll();
    }
}
