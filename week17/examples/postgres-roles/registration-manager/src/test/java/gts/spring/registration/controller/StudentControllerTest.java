package gts.spring.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gts.spring.registration.dto.StudentDTO;
import gts.spring.registration.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllStudents() throws Exception {
        StudentDTO dto = buildTestStudentDTO(1L, "First", "Last", "111-111-1111",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "FULL_TIME");

        when(studentService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(dto.getId()))
                .andExpect(jsonPath("$[0].firstName").value(dto.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(dto.getLastName()))
                .andExpect(jsonPath("$[0].phoneNumber").value(dto.getPhoneNumber()))
                .andExpect(jsonPath("$[0].dateOfBirth").value(dto.getDateOfBirth().toString()))
                .andExpect(jsonPath("$[0].status").value(dto.getStatus()));
    }

    @Test
    void shouldReturnStudentById() throws Exception {
        StudentDTO dto = buildTestStudentDTO(2L, "Second", "Last", "222-222-2222",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "PART_TIME");

        when(studentService.findById(2L)).thenReturn(dto);

        mockMvc.perform(get("/api/students/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(dto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(dto.getLastName()))
                .andExpect(jsonPath("$.phoneNumber").value(dto.getPhoneNumber()))
                .andExpect(jsonPath("$.dateOfBirth").value(dto.getDateOfBirth().toString()))
                .andExpect(jsonPath("$.status").value(dto.getStatus()));
    }

    @Test
    void shouldRespondWith404OnGetIfStudentNotFound() throws Exception {
        when(studentService.findById(any())).thenReturn(null);

        mockMvc.perform(get("/api/students/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewStudent() throws Exception {
        StudentDTO request = buildTestStudentDTO(null, "Third", "Last", "333-333-3333",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "HIBERNATING");
        StudentDTO response = buildTestStudentDTO(3L, "Third", "Last", "333-333-3333",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "HIBERNATING");

        when(studentService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.firstName").value(request.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(request.getLastName()))
                .andExpect(jsonPath("$.phoneNumber").value(request.getPhoneNumber()))
                .andExpect(jsonPath("$.dateOfBirth").value(request.getDateOfBirth().toString()))
                .andExpect(jsonPath("$.status").value(request.getStatus()));
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        StudentDTO request = buildTestStudentDTO(4L, "Fourth", "Last", "444-444-4444",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "FULL_TIME");
        StudentDTO response = buildTestStudentDTO(4L, "Fourth", "Last", "444-444-4444",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "FULL_TIME");

        when(studentService.findById(4L)).thenReturn(response);
        when(studentService.update(Mockito.eq(4L), any())).thenReturn(response);

        mockMvc.perform(put("/api/students/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.firstName").value(request.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(request.getLastName()))
                .andExpect(jsonPath("$.phoneNumber").value(request.getPhoneNumber()))
                .andExpect(jsonPath("$.dateOfBirth").value(request.getDateOfBirth().toString()))
                .andExpect(jsonPath("$.status").value(request.getStatus()));
    }

    @Test
    void shouldRespondWith404OnUpdateIfStudentNotFound() throws Exception {
        StudentDTO request = buildTestStudentDTO(4L, "Fourth", "Last", "444-444-4444",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "FULL_TIME");

        when(studentService.findById(4L)).thenReturn(null);

        mockMvc.perform(put("/api/students/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteStudent() throws Exception {
        mockMvc.perform(delete("/api/students/4"))
                .andExpect(status().isNoContent());
    }

    private StudentDTO buildTestStudentDTO(Long id, String firstName, String lastName,
                                           String phoneNumber, LocalDate dateOfBirth, String status) {
        return StudentDTO.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .dateOfBirth(dateOfBirth)
                .status(status)
                .build();
    }
}