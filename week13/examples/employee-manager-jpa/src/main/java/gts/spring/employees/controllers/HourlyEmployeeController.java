package gts.spring.employees.controllers;

import gts.spring.employees.domain.HourlyEmployee;
import gts.spring.employees.services.HourlyEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/employees/hourly")
@RequiredArgsConstructor
public class HourlyEmployeeController {

    private final HourlyEmployeeService hourlyEmployeeService;

    @GetMapping
    public ResponseEntity<Iterable<HourlyEmployee>> getHourlyEmployees() {
        return ResponseEntity.ok(hourlyEmployeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HourlyEmployee> getHourlyEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(hourlyEmployeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<HourlyEmployee> addHourlyEmployee(@RequestBody HourlyEmployee employee) {
        return ResponseEntity.ok(hourlyEmployeeService.createEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HourlyEmployee> updateHourlyEmployee(@RequestBody HourlyEmployee employee, @PathVariable Long id) {
        if (!Objects.equals(employee.getId(), id)) {
            return ResponseEntity.badRequest().build();
        }
        var updatedEmployee = hourlyEmployeeService.updateEmployee(employee);
        return updatedEmployee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHourlyEmployee(@PathVariable Long id) {
        hourlyEmployeeService.deleteEmployeeById(id);
        return ResponseEntity.accepted().build();
    }
}
