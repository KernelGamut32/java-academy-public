package gts.spring.registration;
import gts.spring.registration.domain.Course;
import gts.spring.registration.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("integration")
public class RegistrationApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CourseService courseService;

//	@MockitoBean
//	private CourseService courseServiceMock;

	private String baseUrl;

	@BeforeEach
	public void setUp() {
		baseUrl = "http://localhost:" + port + "/admin/courses";
	}

	@Test
	public void testGetAllCourses() {
		ResponseEntity<Course[]> response = restTemplate.getForEntity(baseUrl, Course[].class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
	}

//	@Test
//	public void testGetAllCourses() {
//		when(courseServiceMock.getAllCourses()).thenReturn(getCourses());
//		ResponseEntity<Course[]> response = restTemplate.getForEntity(baseUrl, Course[].class);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(response.getBody()).isNotNull();
//	}

	@Test
	public void testGetCourseById() {
		Course course = Course.builder()
				.title("Introduction to Computer Science")
				.code("CS101")
				.build();
		course = courseService.createCourse(course);
		ResponseEntity<Course> response = restTemplate.getForEntity(baseUrl + "/" + course.getId(), Course.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(course);
	}

//	@Test
//	public void testGetCourseById() {
//		Course course = Course.builder()
//				.title("Software Engineering")
//				.code("CS105")
//				.build();
//		course.setId(1000L);
//		when(courseServiceMock.createCourse(course)).thenReturn(course);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Course> request = new HttpEntity<>(course, headers);
//
//		ResponseEntity<Course> createResponse = restTemplate.postForEntity(baseUrl, request, Course.class);
//
//		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(createResponse.getBody()).isNotNull();
//		assertThat(createResponse.getBody().getCode()).isEqualTo("CS105");
//
//		when(courseServiceMock.getCourseById(course.getId())).thenReturn(course);
//
//		ResponseEntity<Course> getByIdResponse = restTemplate.getForEntity(baseUrl + "/" + course.getId(), Course.class);
//
//		assertThat(getByIdResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(getByIdResponse.getBody()).isEqualTo(course);
//	}

	@Test
	public void testAddCourse() {
		Course course = Course.builder()
				.title("Data Structures")
				.code("CS102")
				.build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Course> request = new HttpEntity<>(course, headers);

		ResponseEntity<Course> response = restTemplate.postForEntity(baseUrl, request, Course.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getId()).isNotNull();
	}

//	@Test
//	public void testAddCourse() {
//		Course course = Course.builder()
//				.title("Data Structures")
//				.code("CS102")
//				.build();
//		course.setId(1000L);
//		when(courseServiceMock.createCourse(course)).thenReturn(course);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Course> request = new HttpEntity<>(course, headers);
//
//		ResponseEntity<Course> response = restTemplate.postForEntity(baseUrl, request, Course.class);
//
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(response.getBody()).isNotNull();
//		assertThat(response.getBody()).isEqualTo(course);
//	}

	@Test
	public void testUpdateCourse() {
		Course course = Course.builder()
				.title("Algorithms")
				.code("CS103")
				.build();
		course = courseService.createCourse(course);
		course.setTitle("Advanced Algorithms");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Course> request = new HttpEntity<>(course, headers);

		ResponseEntity<Course> response = restTemplate.exchange(baseUrl + "/" + course.getId(), HttpMethod.PUT, request, Course.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getTitle()).isEqualTo("Advanced Algorithms");
	}

//	@Test
//	public void testUpdateCourse() {
//		Course course = Course.builder()
//				.title("Algorithms")
//				.code("CS103")
//				.build();
//		course.setId(1000L);
//		when(courseServiceMock.createCourse(course)).thenReturn(course);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Course> request = new HttpEntity<>(course, headers);
//
//		ResponseEntity<Course> createResponse = restTemplate.postForEntity(baseUrl, request, Course.class);
//
//		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(createResponse.getBody()).isNotNull();
//		assertThat(createResponse.getBody()).isEqualTo(course);
//
//		course.setTitle("Advanced Algorithms");
//
//		when(courseServiceMock.updateCourse(course)).thenReturn(true);
//
//		headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		request = new HttpEntity<>(course, headers);
//
//		ResponseEntity<Course> updateResponse = restTemplate.exchange(baseUrl + "/" + course.getId(), HttpMethod.PUT, request, Course.class);
//
//		assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(Objects.requireNonNull(updateResponse.getBody()).getTitle()).isEqualTo("Advanced Algorithms");
//	}

	@Test
	public void testDeleteCourse() {
		Course course = Course.builder()
				.title("Operating Systems")
				.code("CS104")
				.build();
		course = courseService.createCourse(course);

		restTemplate.delete(baseUrl + "/" + course.getId());
		ResponseEntity<Course> response = restTemplate.getForEntity(baseUrl + "/" + course.getId(), Course.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNull();
	}

//	@Test
//	public void testDeleteCourse() {
//		Course course = Course.builder()
//				.title("Operating Systems")
//				.code("CS104")
//				.build();
//		course.setId(1000L);
//		when(courseServiceMock.createCourse(course)).thenReturn(course);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Course> request = new HttpEntity<>(course, headers);
//
//		ResponseEntity<Course> createResponse = restTemplate.postForEntity(baseUrl, request, Course.class);
//
//		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(createResponse.getBody()).isNotNull();
//		assertThat(createResponse.getBody()).isEqualTo(course);
//
//		when(courseServiceMock.deleteCourse(course.getId())).thenReturn(true);
//
//		restTemplate.delete(baseUrl + "/" + course.getId());
//		ResponseEntity<Course> response = restTemplate.getForEntity(baseUrl + "/" + course.getId(), Course.class);
//
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(response.getBody()).isNull();
//	}

	private List<Course> getCourses() {
		List<Course> courses = new ArrayList<>();
		courses.add(Course.builder().title("Introduction to Computer Science").code("CS101").build());
		courses.add(Course.builder().title("Data Structures").code("CS102").build());
		return courses;
	}
}
