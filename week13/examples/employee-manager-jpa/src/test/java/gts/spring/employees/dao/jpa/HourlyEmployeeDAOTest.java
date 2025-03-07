package gts.spring.employees.dao.jpa;

import gts.spring.employees.domain.HourlyEmployee;
import org.apache.commons.collections4.IteratorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@SpringBootTest
@Tag("unit")
@AutoConfigureTestDatabase
@Transactional
public class HourlyEmployeeDAOTest {

    private HourlyEmployee hourlyEmployee1;
    private HourlyEmployee hourlyEmployee2;

    @Autowired
    private HourlyEmployeeDAO dao;

    @BeforeEach
    void setUp() {
        hourlyEmployee1 = createTestHourlyEmployee("Melissa Testing", "Programmer I", 30, 25);
        hourlyEmployee2 = createTestHourlyEmployee("Joe Schmoe", "Programmer II", 32, 22);

        hourlyEmployee1 = dao.save(hourlyEmployee1);
        hourlyEmployee2 = dao.save(hourlyEmployee2);
    }

    @Test
    public void testFindAllHourlyEmployees() {
        // Arrange - handled in setUp
        // Act
        Iterable<HourlyEmployee> hourlyEmployees = dao.findAll();
        var expectedHourlyEmployees = IteratorUtils.toList(hourlyEmployees.iterator());
        // Assert
        assertAll(
                () -> assertNotNull(hourlyEmployees),
                () -> assertEquals(2, expectedHourlyEmployees.size()),
                () -> assertEquals(0, expectedHourlyEmployees.stream().filter(hourlyEmployee -> hourlyEmployee.getId() == 0).count()),
                () -> assertEquals(hourlyEmployee1, expectedHourlyEmployees.getFirst()),
                () -> assertEquals(hourlyEmployee2, expectedHourlyEmployees.get(1))
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

        // Act
        dao.save(hourlyEmployeeToUpdate);
        hourlyEmployeeToUpdate.setName("Bob Robertson");
        hourlyEmployeeToUpdate.setJobTitle("Basket Weaver");
        hourlyEmployeeToUpdate.setHoursWorked(22.22);
        hourlyEmployeeToUpdate.setHourlyPayRate(10.10);
        dao.save(hourlyEmployeeToUpdate);
        Optional<HourlyEmployee> employeeAfterUpdate = dao.findById(hourlyEmployeeToUpdate.getId());

        // Assert
        assertTrue(employeeAfterUpdate.isPresent());
        checkForEqualityBetweenHourlyEmployees(hourlyEmployeeToUpdate, employeeAfterUpdate.get());
    }

    @Test
    public void testDeleteHourlyEmployee() {
        // Arrange - handled in setUp
        // Act
        dao.deleteById(hourlyEmployee1.getId());
        List<HourlyEmployee> remainingHourlyEmployees = IteratorUtils.toList(dao.findAll().iterator());
        // Assert
        assertAll(
                () -> assertNotNull(remainingHourlyEmployees),
                () -> assertEquals(1, remainingHourlyEmployees.size()),
                () -> assertEquals(hourlyEmployee2, remainingHourlyEmployees.getFirst())
        );
    }

    @Test
    public void testAttemptToDeleteNonExistentHourlyEmployee() {
        // Arrange
        HourlyEmployee nonExistentHourlyEmployee = HourlyEmployee.builder().build();
        nonExistentHourlyEmployee.setId(-1L);
        // Act
        dao.deleteById(nonExistentHourlyEmployee.getId());
        // Assert
        // Verifies successful execution - no errors
    }

    private HourlyEmployee createTestHourlyEmployee(String name, String jobTitle, double hoursWorked, double hourlyPayRate) {

        return HourlyEmployee.builder()
                .name(name)
                .jobTitle(jobTitle)
                .hoursWorked(hoursWorked)
                .hourlyPayRate(hourlyPayRate).build();
    }

    private void checkForEqualityBetweenHourlyEmployees(HourlyEmployee expected, HourlyEmployee actual) {
        assertAll(
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getJobTitle(), actual.getJobTitle()),
                () -> assertEquals(expected.getHoursWorked(), actual.getHoursWorked()),
                () -> assertEquals(expected.getHourlyPayRate(), actual.getHourlyPayRate())
        );
    }
}
