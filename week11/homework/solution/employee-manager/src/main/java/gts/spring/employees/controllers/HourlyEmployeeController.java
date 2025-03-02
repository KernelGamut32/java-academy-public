package gts.spring.employees.controllers;

import gts.spring.employees.domain.HourlyEmployee;
import gts.spring.employees.services.HourlyEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employees/hourly")
@RequiredArgsConstructor
public class HourlyEmployeeController {

    private final HourlyEmployeeService hourlyEmployeeService;

    @GetMapping
    public ResponseEntity<List<HourlyEmployee>> getHourlyEmployees() {
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
        boolean result = hourlyEmployeeService.updateEmployee(employee);
        return result ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        boolean result = hourlyEmployeeService.deleteEmployee(id);
        return result ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
