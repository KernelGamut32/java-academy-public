package gts.spring.employees.dao.inmemory;

import gts.spring.employees.domain.HourlyEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
public class InMemoryHourlyEmployeeDAOTest {

    private HourlyEmployee hourlyEmployee1;
    private HourlyEmployee hourlyEmployee2;

    private InMemoryHourlyEmployeeDAO dao = new InMemoryHourlyEmployeeDAO();

    @BeforeEach
    void setUp() {
        dao = new InMemoryHourlyEmployeeDAO();
        dao.resetDataStore();

        hourlyEmployee1 = createTestHourlyEmployee("Melissa Testing", "Programmer I", 30, 25);
        hourlyEmployee2 = createTestHourlyEmployee("Joe Schmoe", "Programmer II", 32, 22);

        hourlyEmployee1 = dao.insert(hourlyEmployee1);
        hourlyEmployee2 = dao.insert(hourlyEmployee2);
    }

    @Test
    public void testFindAllHourlyEmployees() {
        // Arrange - handled in setUp
        // Act
        List<HourlyEmployee> hourlyEmployees = dao.findAll();
        // Assert
        assertAll(
                () -> assertNotNull(hourlyEmployees),
                () -> assertEquals(2, hourlyEmployees.size()),
                () -> assertEquals(0, hourlyEmployees.stream().filter(hourlyEmployee -> hourlyEmployee.getId() == 0).count()),
                () -> assertEquals(hourlyEmployee1, hourlyEmployees.getFirst()),
                () -> assertEquals(hourlyEmployee2, hourlyEmployees.get(1))
        );
    }

    @Test
    public void testFindHourlyEmployeeById() {
        // Arrange - handled in setUp
        // Act
        Optional<HourlyEmployee> foundHourlyEmployee = dao.findById(hourlyEmployee1.getId());
        // Assert
        assertTrue(foundHourlyEmployee.isPresent());
        assertEquals(hourlyEmployee1, foundHourlyEmployee.get());
    }

    @Test
    public void testUpdateHourlyEmployee() {
        // Arrange
        HourlyEmployee hourlyEmployeeToUpdate = createTestHourlyEmployee("Bob Roberts", "Programmer III", 37, 17);
        hourlyEmployeeToUpdate.setId(hourlyEmployee2.getId());
        // Act
        boolean updated = dao.update(hourlyEmployeeToUpdate);
        Optional<HourlyEmployee> verifyHourlyEmployee = dao.findById(hourlyEmployee2.getId());
        // Assert
        assertTrue(verifyHourlyEmployee.isPresent());
        assertTrue(updated);
        assertNotEquals(hourlyEmployee2.getName(), verifyHourlyEmployee.get().getName());
        assertNotEquals(hourlyEmployee2.getJobTitle(), verifyHourlyEmployee.get().getJobTitle());
        assertNotEquals(hourlyEmployee2.getHoursWorked(), verifyHourlyEmployee.get().getHoursWorked());
        assertNotEquals(hourlyEmployee2.getHourlyPayRate(), verifyHourlyEmployee.get().getHourlyPayRate());
        assertEquals(hourlyEmployeeToUpdate, verifyHourlyEmployee.get());
    }

    @Test
    public void testDeleteHourlyEmployee() {
        // Arrange - handled in setUp
        // Act
        boolean deleted = dao.delete(hourlyEmployee1);
        List<HourlyEmployee> remainingHourlyEmployees = dao.findAll();
        // Assert
        assertAll(
                () -> assertTrue(deleted),
                () -> assertNotNull(remainingHourlyEmployees),
                () -> assertEquals(1, remainingHourlyEmployees.size()),
                () -> assertEquals(hourlyEmployee2, remainingHourlyEmployees.getFirst())
        );
    }

    @Test
    public void testAttemptToUpdateNonExistentHourlyEmployee() {
        // Arrange
        HourlyEmployee nonExistentHourlyEmployee = HourlyEmployee.builder().build();
        nonExistentHourlyEmployee.setId(-1L);
        // Act
        boolean updated = dao.update(nonExistentHourlyEmployee);
        // Assert
        assertFalse(updated);
    }

    @Test
    public void testAttemptToDeleteNonExistentHourlyEmployee() {
        // Arrange
        HourlyEmployee nonExistentHourlyEmployee = HourlyEmployee.builder().build();
        nonExistentHourlyEmployee.setId(-1L);
        // Act
        boolean deleted = dao.delete(nonExistentHourlyEmployee);
        // Assert
        assertFalse(deleted);
    }

    private HourlyEmployee createTestHourlyEmployee(String name, String jobTitle, double hoursWorked, double hourlyPayRate) {

        return HourlyEmployee.builder()
                .name(name)
                .jobTitle(jobTitle)
                .hoursWorked(hoursWorked)
                .hourlyPayRate(hourlyPayRate).build();
    }
}
