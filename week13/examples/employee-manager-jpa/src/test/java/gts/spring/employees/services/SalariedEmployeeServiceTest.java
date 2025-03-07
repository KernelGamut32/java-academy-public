package gts.spring.employees.services;

import gts.spring.employees.dao.jpa.SalariedEmployeeDAO;
import gts.spring.employees.domain.SalariedEmployee;
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
public class SalariedEmployeeServiceTest {

    private SalariedEmployee salariedEmployee1;

    @InjectMocks
    private SalariedEmployeeService salariedEmployeeService;

    @Mock
    private SalariedEmployeeDAO salariedEmployeeDAO;

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
        List<SalariedEmployee> results = IteratorUtils.toList(salariedEmployeeService.getAllEmployees().iterator());

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
        Mockito.when(salariedEmployeeDAO.save(salariedEmployee1)).thenReturn(salariedEmployee1);
        Mockito.when(salariedEmployeeDAO.findById(1L)).thenReturn(Optional.ofNullable(salariedEmployee1));

        // Act
        SalariedEmployee createdSalariedEmployee = salariedEmployeeService.createEmployee(salariedEmployee1);

        // Assert
        SalariedEmployee result = salariedEmployeeService.getEmployeeById(createdSalariedEmployee.getId());

        assertEquals(salariedEmployee1, result);

        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).save(salariedEmployee1);
        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testDeleteSalariedEmployee() {
        // Arrange

        // Act
        salariedEmployeeService.deleteEmployeeById(1L);

        // Assert
        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteNonExistentSalariedEmployee() {
        // Arrange

        // Act
        salariedEmployeeService.deleteEmployeeById(9999L);

        // Assert
        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).deleteById(9999L);
    }

    @Test
    public void testUpdateSalariedEmployee() {
        // Arrange
        Mockito.when(salariedEmployeeDAO.save(salariedEmployee1)).thenReturn(salariedEmployee1);

        // Act
        var updatedEmployee = salariedEmployeeService.updateEmployee(salariedEmployee1);

        //Assert
        assertNotNull(updatedEmployee);

        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).save(salariedEmployee1);
    }

    @Test
    public void testUpdateNonExistentSalariedEmployee() {
        // Arrange
        salariedEmployee1.setId(9999L);

        Mockito.when(salariedEmployeeDAO.save(salariedEmployee1)).thenReturn(null);

        // Act
        var updatedEmployee = salariedEmployeeService.updateEmployee(salariedEmployee1);

        // Assert
        assertNull(updatedEmployee);

        Mockito.verify(salariedEmployeeDAO, Mockito.times(1)).save(any());
    }
}
