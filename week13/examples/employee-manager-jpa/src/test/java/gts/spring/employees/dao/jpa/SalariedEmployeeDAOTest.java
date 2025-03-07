package gts.spring.employees.dao.jpa;

import gts.spring.employees.domain.HourlyEmployee;
import gts.spring.employees.domain.SalariedEmployee;
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
public class SalariedEmployeeDAOTest {

    private SalariedEmployee salariedEmployee1;
    private SalariedEmployee salariedEmployee2;

    @Autowired
    private SalariedEmployeeDAO dao;

    @BeforeEach
    void setUp() {
        salariedEmployee1 = createTestSalariedEmployee("Mickey Mouse", "Programmer III", 22000);
        salariedEmployee2 = createTestSalariedEmployee("Donald Duck", "Programmer II", 33000);

        salariedEmployee1 = dao.save(salariedEmployee1);
        salariedEmployee2 = dao.save(salariedEmployee2);
    }

    @Test
    public void testFindAllSalariedEmployees() {
        // Arrange - handled in setUp
        // Act
        Iterable<SalariedEmployee> salariedEmployees = dao.findAll();
        var expectedSalariedEmployees = IteratorUtils.toList(salariedEmployees.iterator());
        // Assert
        assertAll(
                () -> assertNotNull(salariedEmployees),
                () -> assertEquals(2, expectedSalariedEmployees.size()),
                () -> assertEquals(0, expectedSalariedEmployees.stream().filter(salariedEmployee -> salariedEmployee.getId() == 0).count()),
                () -> assertEquals(salariedEmployee1, expectedSalariedEmployees.getFirst()),
                () -> assertEquals(salariedEmployee2, expectedSalariedEmployees.get(1))
        );
    }

    @Test
    public void testFindSalariedEmployeeById() {
        // Arrange - handled in setUp
        // Act
        Optional<SalariedEmployee> foundSalariedEmployee = dao.findById(salariedEmployee1.getId());
        // Assert
        assertTrue(foundSalariedEmployee.isPresent());
        assertEquals(salariedEmployee1, foundSalariedEmployee.get());
    }

    @Test
    public void testUpdateSalariedEmployee() {
        // Arrange
        SalariedEmployee salariedEmployeeToUpdate = createTestSalariedEmployee("Minnie Mouse", "Programmer IV", 75000);

        // Act
        dao.save(salariedEmployeeToUpdate);
        salariedEmployeeToUpdate.setName("Darth Vader");
        salariedEmployeeToUpdate.setJobTitle("Senior Sith Lord");
        salariedEmployeeToUpdate.setYearlySalary(75001);
        dao.save(salariedEmployeeToUpdate);
        Optional<SalariedEmployee> employeeAfterUpdate = dao.findById(salariedEmployeeToUpdate.getId());
        // Assert
        assertTrue(employeeAfterUpdate.isPresent());
        checkForEqualityBetweenSalariedEmployees(salariedEmployeeToUpdate, employeeAfterUpdate.get());
    }

    @Test
    public void testDeleteSalariedEmployee() {
        // Arrange - handled in setUp
        // Act
        dao.deleteById(salariedEmployee1.getId());
        List<SalariedEmployee> remainingSalariedEmployees = IteratorUtils.toList(dao.findAll().iterator());
        // Assert
        assertAll(
                () -> assertNotNull(remainingSalariedEmployees),
                () -> assertEquals(1, remainingSalariedEmployees.size()),
                () -> assertEquals(salariedEmployee2, remainingSalariedEmployees.getFirst())
        );
    }

    @Test
    public void testAttemptToDeleteNonExistentSalariedEmployee() {
        // Arrange
        SalariedEmployee nonExistentSalariedEmployee = SalariedEmployee.builder().build();
        nonExistentSalariedEmployee.setId(-1L);
        // Act
        dao.deleteById(nonExistentSalariedEmployee.getId());
        // Assert
        // Verifies successful execution - no errors
    }

    private SalariedEmployee createTestSalariedEmployee(String name, String jobTitle, double yearlySalary) {

        return SalariedEmployee.builder()
                .name(name)
                .jobTitle(jobTitle)
                .yearlySalary(yearlySalary).build();
    }

    private void checkForEqualityBetweenSalariedEmployees(SalariedEmployee expected, SalariedEmployee actual) {
        assertAll(
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getJobTitle(), actual.getJobTitle()),
                () -> assertEquals(expected.getYearlySalary(), actual.getYearlySalary())
        );
    }
}
