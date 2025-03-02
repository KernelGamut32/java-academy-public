package gts.spring.employees.services;

import gts.spring.employees.dao.BaseDAO;
import gts.spring.employees.domain.HourlyEmployee;
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
public class HourlyEmployeeServiceTest {

    private HourlyEmployee hourlyEmployee1;

    @InjectMocks
    private HourlyEmployeeService hourlyEmployeeService;

    @Mock
    private BaseDAO<HourlyEmployee> hourlyEmployeeDAO;

    @BeforeEach
    public void setUp() {
        hourlyEmployee1 = HourlyEmployee.builder()
                .name("Test Employee")
                .jobTitle("Head Scratcher")
                .hoursWorked(32.5)
                .hourlyPayRate(10.50)
                .build();
        hourlyEmployee1.setId(1L);
    }

    @Test
    public void testFindAllHourlyEmployees() {
        // Arrange
        Mockito.when(hourlyEmployeeDAO.findAll()).thenReturn(List.of(hourlyEmployee1));

        // Act
        List<HourlyEmployee> results = hourlyEmployeeService.getAllEmployees();

        // Assert
        assertAll(
                () -> assertNotNull(results),
                () -> assertEquals(1, results.size()),
                () -> assertEquals(hourlyEmployee1, results.getFirst())
        );

        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).findAll();
    }

    @Test
    public void testCreateHourlyEmployee() {
        // Arrange
        Mockito.when(hourlyEmployeeDAO.insert(hourlyEmployee1)).thenReturn(hourlyEmployee1);
        Mockito.when(hourlyEmployeeDAO.findById(1L)).thenReturn(Optional.ofNullable(hourlyEmployee1));

        // Act
        HourlyEmployee createdHourlyEmployee = hourlyEmployeeService.createEmployee(hourlyEmployee1);

        // Assert
        HourlyEmployee result = hourlyEmployeeService.getEmployeeById(createdHourlyEmployee.getId());

        assertEquals(hourlyEmployee1, result);

        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).insert(hourlyEmployee1);
        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testDeleteHourlyEmployee() {
        // Arrange
        Mockito.when(hourlyEmployeeDAO.findById(1L)).thenReturn(Optional.ofNullable(hourlyEmployee1));
        Mockito.when(hourlyEmployeeDAO.delete(hourlyEmployee1)).thenReturn(true);

        // Act
        boolean result = hourlyEmployeeService.deleteEmployee(1L);

        // Assert
        assertTrue(result);

        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).findById(1L);
        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).delete(hourlyEmployee1);
    }

    @Test
    public void testDeleteNonExistentHourlyEmployee() {
        // Arrange
        Mockito.when(hourlyEmployeeDAO.findById(9999L)).thenReturn(Optional.empty());

        // Act
        boolean result = hourlyEmployeeService.deleteEmployee(9999L);

        // Assert
        assertFalse(result);

        Mockito.verify(hourlyEmployeeDAO).findById(9999L);
        Mockito.verify(hourlyEmployeeDAO, never()).delete(any());
    }

    @Test
    public void testUpdateHourlyEmployee() {
        // Arrange
        Mockito.when(hourlyEmployeeDAO.findById(1L)).thenReturn(Optional.ofNullable(hourlyEmployee1));
        Mockito.when(hourlyEmployeeDAO.update(hourlyEmployee1)).thenReturn(true);

        // Act
        boolean result = hourlyEmployeeService.updateEmployee(hourlyEmployee1);

        //Assert
        assertTrue(result);

        Mockito.verify(hourlyEmployeeDAO, atMostOnce()).findById(1L);
        Mockito.verify(hourlyEmployeeDAO, atMostOnce()).update(hourlyEmployee1);
    }

    @Test
    public void testUpdateNonExistentHourlyEmployee() {
        // Arrange
        hourlyEmployee1.setId(9999L);

        Mockito.when(hourlyEmployeeDAO.findById(9999L)).thenReturn(Optional.empty());

        // Act
        boolean result = hourlyEmployeeService.updateEmployee(hourlyEmployee1);

        // Assert
        assertFalse(result);

        Mockito.verify(hourlyEmployeeDAO).findById(9999L);
        Mockito.verify(hourlyEmployeeDAO, never()).update(any());
    }
}
