package gts.spring.employees.controllers;

import gts.spring.employees.domain.SalariedEmployee;
import gts.spring.employees.services.SalariedEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/employees/salaried")
@RequiredArgsConstructor
public class SalariedEmployeeController {

    private final SalariedEmployeeService salariedEmployeeService;

    @GetMapping
    public ResponseEntity<Iterable<SalariedEmployee>> getSalariedEmployees() {
        return ResponseEntity.ok(salariedEmployeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalariedEmployee> getSalariedEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(salariedEmployeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<SalariedEmployee> addSalariedEmployee(@RequestBody SalariedEmployee employee) {
        return ResponseEntity.ok(salariedEmployeeService.createEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalariedEmployee> updateSalariedEmployee(@RequestBody SalariedEmployee employee, @PathVariable Long id) {
        if (!Objects.equals(employee.getId(), id)) {
            return ResponseEntity.badRequest().build();
        }
        var updatedEmployee = salariedEmployeeService.updateEmployee(employee);
        return updatedEmployee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalariedEmployee(@PathVariable Long id) {
        salariedEmployeeService.deleteEmployeeById(id);
        return ResponseEntity.accepted().build();
    }
}
