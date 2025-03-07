package gts.spring.employees.services;

import gts.spring.employees.dao.jpa.HourlyEmployeeDAO;
import gts.spring.employees.domain.HourlyEmployee;
import org.apache.commons.collections4.IteratorUtils;
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

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class HourlyEmployeeServiceTest {

    private HourlyEmployee hourlyEmployee1;

    @InjectMocks
    private HourlyEmployeeService hourlyEmployeeService;

    @Mock
    private HourlyEmployeeDAO hourlyEmployeeDAO;

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
        List<HourlyEmployee> results = IteratorUtils.toList(hourlyEmployeeService.getAllEmployees().iterator());

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
        Mockito.when(hourlyEmployeeDAO.save(hourlyEmployee1)).thenReturn(hourlyEmployee1);
        Mockito.when(hourlyEmployeeDAO.findById(1L)).thenReturn(Optional.ofNullable(hourlyEmployee1));

        // Act
        HourlyEmployee createdHourlyEmployee = hourlyEmployeeService.createEmployee(hourlyEmployee1);

        // Assert
        HourlyEmployee result = hourlyEmployeeService.getEmployeeById(createdHourlyEmployee.getId());

        assertEquals(hourlyEmployee1, result);

        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).save(hourlyEmployee1);
        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testDeleteHourlyEmployee() {
        // Arrange

        // Act
        hourlyEmployeeService.deleteEmployeeById(1L);

        // Assert
        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteNonExistentHourlyEmployee() {
        // Arrange

        // Act
        hourlyEmployeeService.deleteEmployeeById(9999L);

        // Assert
        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).deleteById(9999L);
    }

    @Test
    public void testUpdateHourlyEmployee() {
        // Arrange
        Mockito.when(hourlyEmployeeDAO.save(hourlyEmployee1)).thenReturn(hourlyEmployee1);

        // Act
        var updatedEmployee = hourlyEmployeeService.updateEmployee(hourlyEmployee1);

        //Assert
        assertNotNull(updatedEmployee);

        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).save(hourlyEmployee1);
    }

    @Test
    public void testUpdateNonExistentHourlyEmployee() {
        // Arrange
        hourlyEmployee1.setId(9999L);

        Mockito.when(hourlyEmployeeDAO.save(hourlyEmployee1)).thenReturn(null);

        // Act
        var updatedEmployee = hourlyEmployeeService.updateEmployee(hourlyEmployee1);

        // Assert
        assertNull(updatedEmployee);

        Mockito.verify(hourlyEmployeeDAO, Mockito.times(1)).save(any());
    }
}
