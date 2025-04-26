package gts.spring.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gts.spring.registration.dto.CourseDTO;
import gts.spring.registration.service.CourseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllCourses() throws Exception {
        CourseDTO dto = buildTestCourseDTO(1L, "Test Course 1", "T0001", 0.5);

        when(courseService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(dto.getId()))
                .andExpect(jsonPath("$[0].title").value(dto.getTitle()))
                .andExpect(jsonPath("$[0].code").value(dto.getCode()));
    }

    @Test
    void shouldReturnCourseById() throws Exception {
        CourseDTO dto = buildTestCourseDTO(2L, "Test Course 2", "T0002", 1.0);

        when(courseService.findById(2L)).thenReturn(dto);

        mockMvc.perform(get("/api/courses/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(dto.getTitle()))
                .andExpect(jsonPath("$.code").value(dto.getCode()));
    }

    @Test
    void shouldRespondWith404OnGetIfCourseNotFound() throws Exception {
        when(courseService.findById(any())).thenReturn(null);

        mockMvc.perform(get("/api/courses/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewCourse() throws Exception {
        CourseDTO request = buildTestCourseDTO(null, "Test Course 3", "T0003", 1.5);
        CourseDTO response = buildTestCourseDTO(3L, "Test Course 3", "T0003", 1.5);

        when(courseService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.code").value(request.getCode()));
    }

    @Test
    void shouldUpdateCourse() throws Exception {
        CourseDTO request = buildTestCourseDTO(4L, "Test Course 4", "T0004", 2.0);
        CourseDTO response = buildTestCourseDTO(4L, "Test Course 4", "T0004", 2.0);

        when(courseService.findById(4L)).thenReturn(response);
        when(courseService.update(Mockito.eq(4L), any())).thenReturn(response);

        mockMvc.perform(put("/api/courses/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.code").value(request.getCode()))
                .andExpect(jsonPath("$.credits").value(request.getCredits()));
    }

    @Test
    void shouldRespondWith404OnUpdateIfCourseNotFound() throws Exception {
        CourseDTO request = buildTestCourseDTO(4L, "Test Course 4", "T0004", 2.0);

        when(courseService.findById(4L)).thenReturn(null);

        mockMvc.perform(put("/api/courses/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteCourse() throws Exception {
        mockMvc.perform(delete("/api/courses/4"))
                .andExpect(status().isNoContent());
    }

    private CourseDTO buildTestCourseDTO(Long id, String title, String code, double credits) {
        return CourseDTO.builder()
                .id(id)
                .title(title)
                .code(code)
                .credits(credits)
                .build();
    }
}