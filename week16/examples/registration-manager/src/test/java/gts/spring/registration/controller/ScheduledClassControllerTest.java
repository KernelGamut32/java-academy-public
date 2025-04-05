package gts.spring.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gts.spring.registration.dto.CourseDTO;
import gts.spring.registration.dto.ScheduledClassDTO;
import gts.spring.registration.service.ScheduledClassService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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

@WebMvcTest(ScheduledClassController.class)
class ScheduledClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ScheduledClassService scheduledClassService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllScheduledClass() throws Exception {
        ScheduledClassDTO dto = buildTestScheduledClassDTO(1L, "S0001", 
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                CourseDTO.builder().id(1L).build());

        when(scheduledClassService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/classes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(dto.getId()))
                .andExpect(jsonPath("$[0].sectionName").value(dto.getSectionName()))
                .andExpect(jsonPath("$[0].startDate").value(dto.getStartDate().toString()))
                .andExpect(jsonPath("$[0].endDate").value(dto.getEndDate().toString()))
                .andExpect(jsonPath("$[0].course.id").value(dto.getCourse().getId()));
    }

    @Test
    void shouldReturnScheduledClassById() throws Exception {
        ScheduledClassDTO dto = buildTestScheduledClassDTO(2L, "S0002",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                CourseDTO.builder().id(2L).build());

        when(scheduledClassService.findById(2L)).thenReturn(dto);

        mockMvc.perform(get("/api/classes/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sectionName").value(dto.getSectionName()))
                .andExpect(jsonPath("$.startDate").value(dto.getStartDate().toString()))
                .andExpect(jsonPath("$.endDate").value(dto.getEndDate().toString()))
                .andExpect(jsonPath("$.course.id").value(dto.getCourse().getId()));
    }

    @Test
    void shouldRespondWith404OnGetIfScheduledClassNotFound() throws Exception {
        when(scheduledClassService.findById(any())).thenReturn(null);

        mockMvc.perform(get("/api/classes/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewScheduledClass() throws Exception {
        ScheduledClassDTO request = buildTestScheduledClassDTO(null, "S0003",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                CourseDTO.builder().id(3L).build());
        ScheduledClassDTO response = buildTestScheduledClassDTO(3L, "S0003",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                CourseDTO.builder().id(3L).build());

        when(scheduledClassService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.sectionName").value(request.getSectionName()))
                .andExpect(jsonPath("$.startDate").value(request.getStartDate().toString()))
                .andExpect(jsonPath("$.endDate").value(request.getEndDate().toString()))
                .andExpect(jsonPath("$.course.id").value(request.getCourse().getId()));
    }

    @Test
    void shouldRespondWith400OnCreateIfIssuesCreatingClass() throws Exception {
        ScheduledClassDTO request = buildTestScheduledClassDTO(null, "S0003",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                CourseDTO.builder().id(3L).build());

        when(scheduledClassService.create(any())).thenReturn(null);

        mockMvc.perform(post("/api/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateScheduledClass() throws Exception {
        ScheduledClassDTO request = buildTestScheduledClassDTO(4L, "S0004",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                CourseDTO.builder().id(4L).build());
        ScheduledClassDTO response = buildTestScheduledClassDTO(4L, "S0004",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                CourseDTO.builder().id(4L).build());

        when(scheduledClassService.findById(4L)).thenReturn(response);
        when(scheduledClassService.update(Mockito.eq(4L), any())).thenReturn(response);

        mockMvc.perform(put("/api/classes/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.sectionName").value(request.getSectionName()))
                .andExpect(jsonPath("$.startDate").value(request.getStartDate().toString()))
                .andExpect(jsonPath("$.endDate").value(request.getEndDate().toString()))
                .andExpect(jsonPath("$.course.id").value(request.getCourse().getId()));
    }

    @Test
    void shouldRespondWith404OnUpdateIfScheduledClassNotFound() throws Exception {
        ScheduledClassDTO request = buildTestScheduledClassDTO(4L, "S0004",
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                CourseDTO.builder().id(4L).build());

        when(scheduledClassService.findById(4L)).thenReturn(null);

        mockMvc.perform(put("/api/classes/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteScheduledClass() throws Exception {
        mockMvc.perform(delete("/api/classes/4"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldRegisterStudentToClass() throws Exception {
        Long classId = 10L;
        Long studentId = 20L;

        when(scheduledClassService.registerStudent(classId, studentId)).thenReturn(new ScheduledClassDTO());

        mockMvc.perform(post("/api/classes/{classId}/register/{studentId}", classId, studentId))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRespondWith404OnRegisterStudentIfIssuesRegistering() throws Exception {
        Long classId = 10L;
        Long studentId = 20L;

        when(scheduledClassService.registerStudent(classId, studentId)).thenReturn(null);

        mockMvc.perform(post("/api/classes/{classId}/register/{studentId}", classId, studentId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDropStudentFromClass() throws Exception {
        Long classId = 11L;
        Long studentId = 21L;

        when(scheduledClassService.dropStudent(classId, studentId)).thenReturn(new ScheduledClassDTO());

        mockMvc.perform(post("/api/classes/{classId}/drop/{studentId}", classId, studentId))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRespondWith404OnDropStudentIfIssuesDropping() throws Exception {
        Long classId = 10L;
        Long studentId = 20L;

        when(scheduledClassService.dropStudent(classId, studentId)).thenReturn(null);

        mockMvc.perform(post("/api/classes/{classId}/drop/{studentId}", classId, studentId))
                .andExpect(status().isNotFound());
    }

    private ScheduledClassDTO buildTestScheduledClassDTO(Long id, String sectionName, LocalDate startDate,
                                           LocalDate endDate, CourseDTO course) {
        return ScheduledClassDTO.builder()
                .id(id)
                .sectionName(sectionName)
                .startDate(startDate)
                .endDate(endDate)
                .course(course)
                .build();
    }
}