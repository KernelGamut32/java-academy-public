package gts.spring.employees.services;

import gts.spring.employees.dao.BaseDAO;
import gts.spring.employees.domain.SalariedEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class SalariedEmployeeServiceTest {

    private SalariedEmployee salariedEmployee1;

    @InjectMocks
    private SalariedEmployeeService salariedEmployeeService;

    @Mock
    private BaseDAO<SalariedEmployee> salariedEmployeeDAO;

    @BeforeEach
    public void setUp() {
        salariedEmployee1 = SalariedEmployee.builder()
                .name("Demo Employee")
                .jobTitle("Job Worker")
                .yearlySalary(22222.22)
                .build();
        salariedEmployee1.setId(1L);
    }

    @Test
    public void testFindAllSalariedEmployees() {
        // Arrange
        Mockito.when(salariedEmployeeDAO.findAll()).thenReturn(List.of(salariedEmployee1));

        // Act
        List<SalariedEmployee> results = salariedEmployeeService.getAllEmployees();

        // Assert
        assertAll(
                () -> assertNotNull(results),
                () -> assertEquals(1, results.size()),
                () -> assertEquals(salariedEmployee1, results.getFirst())
        );

        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).findAll();
    }

    @Test
    public void testCreateSalariedEmployee() {
        // Arrange
        Mockito.when(salariedEmployeeDAO.insert(salariedEmployee1)).thenReturn(salariedEmployee1);
        Mockito.when(salariedEmployeeDAO.findById(1L)).thenReturn(Optional.ofNullable(salariedEmployee1));

        // Act
        SalariedEmployee createdSalariedEmployee = salariedEmployeeService.createEmployee(salariedEmployee1);

        // Assert
        SalariedEmployee result = salariedEmployeeService.getEmployeeById(createdSalariedEmployee.getId());

        assertEquals(salariedEmployee1, result);

        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).insert(salariedEmployee1);
        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testDeleteSalariedEmployee() {
        // Arrange
        Mockito.when(salariedEmployeeDAO.findById(1L)).thenReturn(Optional.ofNullable(salariedEmployee1));
        Mockito.when(salariedEmployeeDAO.delete(salariedEmployee1)).thenReturn(true);

        // Act
        boolean result = salariedEmployeeService.deleteEmployee(1L);

        // Assert
        assertTrue(result);

        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).findById(1L);
        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).delete(salariedEmployee1);
    }

    @Test
    public void testDeleteNonExistentSalariedEmployee() {
        // Arrange
        Mockito.when(salariedEmployeeDAO.findById(9999L)).thenReturn(Optional.empty());

        // Act
        boolean result = salariedEmployeeService.deleteEmployee(9999L);

        // Assert
        assertFalse(result);

        Mockito.verify(salariedEmployeeDAO).findById(9999L);
        Mockito.verify(salariedEmployeeDAO, never()).delete(any());
    }

    @Test
    public void testUpdateSalariedEmployee() {
        // Arrange
        Mockito.when(salariedEmployeeDAO.findById(1L)).thenReturn(Optional.ofNullable(salariedEmployee1));
        Mockito.when(salariedEmployeeDAO.update(salariedEmployee1)).thenReturn(true);

        // Act
        boolean result = salariedEmployeeService.updateEmployee(salariedEmployee1);

        //Assert
        assertTrue(result);

        Mockito.verify(salariedEmployeeDAO, atMostOnce()).findById(1L);
        Mockito.verify(salariedEmployeeDAO, atMostOnce()).update(salariedEmployee1);
    }

    @Test
    public void testUpdateNonExistentSalariedEmployee() {
        // Arrange
        salariedEmployee1.setId(9999L);

        Mockito.when(salariedEmployeeDAO.findById(9999L)).thenReturn(Optional.empty());

        // Act
        boolean result = salariedEmployeeService.updateEmployee(salariedEmployee1);

        // Assert
        assertFalse(result);

        Mockito.verify(salariedEmployeeDAO).findById(9999L);
        Mockito.verify(salariedEmployeeDAO, never()).update(any());
    }
}
