package gts.spring.employees.controllers;

import gts.spring.employees.domain.SalariedEmployee;
import gts.spring.employees.services.SalariedEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employees/salaried")
@RequiredArgsConstructor
public class SalariedEmployeeController {

    private final SalariedEmployeeService salariedEmployeeService;

    @GetMapping
    public ResponseEntity<List<SalariedEmployee>> getSalariedEmployees() {
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
        boolean result = salariedEmployeeService.updateEmployee(employee);
        return result ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalariedEmployee(@PathVariable Long id) {
        boolean result = salariedEmployeeService.deleteEmployee(id);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
