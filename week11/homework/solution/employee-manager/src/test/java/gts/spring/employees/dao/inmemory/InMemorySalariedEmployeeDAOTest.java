package gts.spring.employees.dao.inmemory;

import gts.spring.employees.domain.SalariedEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
public class InMemorySalariedEmployeeDAOTest {

    private SalariedEmployee salariedEmployee1;
    private SalariedEmployee salariedEmployee2;

    private InMemorySalariedEmployeeDAO dao = new InMemorySalariedEmployeeDAO();

    @BeforeEach
    void setUp() {
        dao = new InMemorySalariedEmployeeDAO();
        dao.resetDataStore();

        salariedEmployee1 = createTestSalariedEmployee("Mickey Mouse", "Programmer III", 22000);
        salariedEmployee2 = createTestSalariedEmployee("Donald Duck", "Programmer II", 33000);

        salariedEmployee1 = dao.insert(salariedEmployee1);
        salariedEmployee2 = dao.insert(salariedEmployee2);
    }

    @Test
    public void testFindAllSalariedEmployees() {
        // Arrange - handled in setUp
        // Act
        List<SalariedEmployee> salariedEmployees = dao.findAll();
        // Assert
        assertAll(
                () -> assertNotNull(salariedEmployees),
                () -> assertEquals(2, salariedEmployees.size()),
                () -> assertEquals(0, salariedEmployees.stream().filter(salariedEmployee -> salariedEmployee.getId() == 0).count()),
                () -> assertEquals(salariedEmployee1, salariedEmployees.getFirst()),
                () -> assertEquals(salariedEmployee2, salariedEmployees.get(1))
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
        salariedEmployeeToUpdate.setId(salariedEmployee2.getId());
        // Act
        boolean updated = dao.update(salariedEmployeeToUpdate);
        Optional<SalariedEmployee> verifySalariedEmployee = dao.findById(salariedEmployee2.getId());
        // Assert
        assertTrue(verifySalariedEmployee.isPresent());
        assertTrue(updated);
        assertNotEquals(salariedEmployee2.getName(), verifySalariedEmployee.get().getName());
        assertNotEquals(salariedEmployee2.getJobTitle(), verifySalariedEmployee.get().getJobTitle());
        assertNotEquals(salariedEmployee2.getYearlySalary(), verifySalariedEmployee.get().getYearlySalary());
        assertEquals(salariedEmployeeToUpdate, verifySalariedEmployee.get());
    }

    @Test
    public void testDeleteSalariedEmployee() {
        // Arrange - handled in setUp
        // Act
        boolean deleted = dao.delete(salariedEmployee1);
        List<SalariedEmployee> remainingSalariedEmployees = dao.findAll();
        // Assert
        assertAll(
                () -> assertTrue(deleted),
                () -> assertNotNull(remainingSalariedEmployees),
                () -> assertEquals(1, remainingSalariedEmployees.size()),
                () -> assertEquals(salariedEmployee2, remainingSalariedEmployees.getFirst())
        );
    }

    @Test
    public void testAttemptToUpdateNonExistentSalariedEmployee() {
        // Arrange
        SalariedEmployee nonExistentSalariedEmployee = SalariedEmployee.builder().build();
        nonExistentSalariedEmployee.setId(-1L);
        // Act
        boolean updated = dao.update(nonExistentSalariedEmployee);
        // Assert
        assertFalse(updated);
    }

    @Test
    public void testAttemptToDeleteNonExistentSalariedEmployee() {
        // Arrange
        SalariedEmployee nonExistentSalariedEmployee = SalariedEmployee.builder().build();
        nonExistentSalariedEmployee.setId(-1L);
        // Act
        boolean deleted = dao.delete(nonExistentSalariedEmployee);
        // Assert
        assertFalse(deleted);
    }

    private SalariedEmployee createTestSalariedEmployee(String name, String jobTitle, double yearlySalary) {

        return SalariedEmployee.builder()
                .name(name)
                .jobTitle(jobTitle)
                .yearlySalary(yearlySalary).build();
    }
}
