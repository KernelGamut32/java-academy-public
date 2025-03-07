package gts.spring.employees.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import gts.spring.employees.domain.HourlyEmployee;
import gts.spring.employees.services.HourlyEmployeeService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Tag("integration")
public class HourlyEmployeeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HourlyEmployeeService hourlyEmployeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllHourlyEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/hourly"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetHourlyEmployeeById() throws Exception {
        HourlyEmployee hourlyEmployee = HourlyEmployee.builder()
                .name("Worker One")
                .jobTitle("The Stuff Doer")
                .hoursWorked(11.11)
                .hourlyPayRate(22.22)
                .build();
        hourlyEmployee = hourlyEmployeeService.createEmployee(hourlyEmployee);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/hourly/" + hourlyEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(hourlyEmployee.getId()));
    }

    @Test
    public void testAddHourlyEmployee() throws Exception {
        HourlyEmployee hourlyEmployee = HourlyEmployee.builder()
                .name("Worker Two")
                .jobTitle("Head Basket Weaver")
                .hoursWorked(25.25)
                .hourlyPayRate(12.27)
                .build();
        mockMvc.perform(post("/employees/hourly")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hourlyEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(hourlyEmployee.getName()));
    }

    @Test
    public void testUpdateHourlyEmployee() throws Exception {
        HourlyEmployee hourlyEmployee = HourlyEmployee.builder()
                .name("Worker Three")
                .jobTitle("Junior Basket Weaver")
                .hoursWorked(18.10)
                .hourlyPayRate(17.75)
                .build();
        hourlyEmployee = hourlyEmployeeService.createEmployee(hourlyEmployee);
        hourlyEmployee.setJobTitle("Advanced Junior Basket Weaver");

        mockMvc.perform(put("/employees/hourly/" + hourlyEmployee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hourlyEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobTitle").value("Advanced Junior Basket Weaver"));
    }

    @Test
    public void testDeleteHourlyEmployee() throws Exception {
        HourlyEmployee hourlyEmployee = HourlyEmployee.builder()
                .name("Worker Four")
                .jobTitle("Lead Napper")
                .hoursWorked(10.00)
                .hourlyPayRate(3.50)
                .build();
        hourlyEmployee = hourlyEmployeeService.createEmployee(hourlyEmployee);

        mockMvc.perform(delete("/employees/hourly/" + hourlyEmployee.getId()))
                .andExpect(status().isAccepted());

        mockMvc.perform(get("/employees/hourly/" + hourlyEmployee.getId()))
                .andExpect(status().isOk());
    }
}
