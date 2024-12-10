package expeditors.week04.solutions.employees.service;

import expeditors.week04.solutions.employees.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeService service;

    @BeforeEach
    void setUp() {
        service = new EmployeeService();
    }

    @Test
    @DisplayName("Validates employee creation using constructor")
    void createEmployeeConstructor() {
        service.createEmployee("Employee One", "Worker I", 52000.00);
        var employees = service.getEmployeesByName("Employee One");
        var employeeById = service.getEmployeeById(employees.getFirst().getId());
        assertAll(() -> assertEquals(1, employees.size()),
                () -> assertEquals(employeeById, employees.getFirst()),
                () -> assertEquals("Employee One", employeeById.getName()),
                () -> assertEquals("Worker I", employeeById.getJobTitle()),
                () -> assertEquals(52000.00, employeeById.getYearlySalary()));
    }

    @Test
    @DisplayName("Validates employee creation using individual attributes")
    void createEmployeeIndividualAttributes() {
        var employee = new Employee();
        employee.setName("Employee Two");
        employee.setJobTitle("Worker II");
        employee.setYearlySalary(62000.00);
        service.createEmployee(employee);
        var employees = service.getEmployeesByName("Employee Two");
        var employeeById = service.getEmployeeById(employees.getFirst().getId());
        assertAll(() -> assertEquals(1, employees.size()),
                () -> assertEquals(employeeById, employees.getFirst()));
    }

    @Test
    @DisplayName("Validates updates to employee")
    void updateEmployee() {
        service.createEmployee("Employee Three", "Worker III", 72000.00);
        var employeeForUpdate = service.getEmployeesByName("Employee Three").getFirst();
        employeeForUpdate.setJobTitle("Worker IIIA");
        employeeForUpdate.setYearlySalary(72001.99);
        service.updateEmployee(employeeForUpdate);
        var employeeById = service.getEmployeeById(employeeForUpdate.getId());
        assertAll(() -> assertEquals("Employee Three", employeeById.getName()),
                () -> assertEquals("Worker IIIA", employeeById.getJobTitle()),
                () -> assertEquals(72001.99, employeeById.getYearlySalary()));
    }

    @Test
    @DisplayName("Validates deletion of employee")
    void deleteEmployee() {
        service.createEmployee("Employee Four", "Worker IV", 81500.00);
        service.createEmployee("Employee Four", "Worker IVB", 77000.00);
        var employeesBeforeDelete = service.getEmployeesByName("Employee Four");
        assertEquals(2, employeesBeforeDelete.size());
        service.deleteEmployee(employeesBeforeDelete.getFirst().getId());
        var employeesAfterDelete = service.getEmployeesByName("Employee Four");
        assertAll(() -> assertEquals(1, employeesAfterDelete.size()),
                () -> assertEquals("Employee Four", employeesAfterDelete.getFirst().getName()),
                () -> assertEquals("Worker IVB", employeesAfterDelete.getFirst().getJobTitle()),
                () -> assertEquals(77000.00, employeesAfterDelete.getFirst().getYearlySalary()),
                () -> assertEquals(77000.00 / 52, employeesAfterDelete.getFirst().getWeeklySalary()));
    }

    @Test
    @DisplayName("Validates getting all employees")
    void getAllEmployees() {
        service.createEmployee("Employee A", "Worker VA", 11000.00);
        service.createEmployee("Employee B", "Worker VB", 12000.00);
        service.createEmployee("Employee C", "Worker VC", 13000.00);
        var employees = service.getAllEmployees();
        var secondEmployee = employees.get(1);
        assertAll(() -> assertEquals(3, employees.size()),
                () -> assertEquals("Employee B", secondEmployee.getName()),
                () -> assertEquals("Worker VB", secondEmployee.getJobTitle()),
                () -> assertEquals(12000.00, secondEmployee.getYearlySalary()),
                () -> assertEquals(12000.00 / 52, secondEmployee.getWeeklySalary()));
    }
}