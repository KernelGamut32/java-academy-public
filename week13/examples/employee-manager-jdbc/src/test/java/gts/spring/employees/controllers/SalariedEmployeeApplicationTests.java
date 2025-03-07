package gts.spring.employees.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import gts.spring.employees.domain.SalariedEmployee;
import gts.spring.employees.services.SalariedEmployeeService;
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
public class SalariedEmployeeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SalariedEmployeeService salariedEmployeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllSalariedEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/salaried"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetSalariedEmployeeById() throws Exception {
        SalariedEmployee salariedEmployee = SalariedEmployee.builder()
                .name("Worker One")
                .jobTitle("The Stuff Doer")
                .yearlySalary(1001)
                .build();
        salariedEmployee = salariedEmployeeService.createEmployee(salariedEmployee);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/salaried/" + salariedEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(salariedEmployee.getId()));
    }

    @Test
    public void testAddSalariedEmployee() throws Exception {
        SalariedEmployee salariedEmployee = SalariedEmployee.builder()
                .name("Worker Two")
                .jobTitle("Head Basket Weaver")
                .yearlySalary(2002)
                .build();
        mockMvc.perform(post("/employees/salaried")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salariedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(salariedEmployee.getName()));
    }

    @Test
    public void testUpdateSalariedEmployee() throws Exception {
        SalariedEmployee salariedEmployee = SalariedEmployee.builder()
                .name("Worker Three")
                .jobTitle("Junior Basket Weaver")
                .yearlySalary(3003)
                .build();
        salariedEmployee = salariedEmployeeService.createEmployee(salariedEmployee);
        salariedEmployee.setJobTitle("Advanced Junior Basket Weaver");

        mockMvc.perform(put("/employees/salaried/" + salariedEmployee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salariedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobTitle").value("Advanced Junior Basket Weaver"));
    }

    @Test
    public void testDeleteSalariedEmployee() throws Exception {
        SalariedEmployee salariedEmployee = SalariedEmployee.builder()
                .name("Worker Four")
                .jobTitle("Lead Napper")
                .yearlySalary(4004)
                .build();
        salariedEmployee = salariedEmployeeService.createEmployee(salariedEmployee);

        mockMvc.perform(delete("/employees/salaried/" + salariedEmployee.getId()))
                .andExpect(status().isAccepted());

        mockMvc.perform(get("/employees/salaried/" + salariedEmployee.getId()))
                .andExpect(status().isOk());
    }
}
