package gts.springBoot.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import gts.springBoot.registration.domain.Course;
import gts.springBoot.registration.service.CourseService;
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
public class AlternativeRegistrationApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllCourses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/courses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetCourseById() throws Exception {
        Course course = new Course("CS101", "Introduction to Computer Science");
        course = courseService.createCourse(course);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/courses/" + course.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(course.getId()));
    }

    @Test
    public void testAddCourse() throws Exception {
        Course course = new Course("CS102", "Data Structures");
        mockMvc.perform(post("/admin/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("CS102"));
    }

    @Test
    public void testUpdateCourse() throws Exception {
        Course course = new Course("CS103", "Algorithms");
        course = courseService.createCourse(course);
        course.setTitle("Advanced Algorithms");

        mockMvc.perform(put("/admin/courses/" + course.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Advanced Algorithms"));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        Course course = new Course("CS104", "Operating Systems");
        course = courseService.createCourse(course);

        mockMvc.perform(delete("/admin/courses/" + course.getId()))
                .andExpect(status().isAccepted());

        mockMvc.perform(get("/admin/courses/" + course.getId()))
                .andExpect(status().isOk());
    }
}
