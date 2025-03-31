package gts.spring.conferences.controller;

import gts.spring.conferences.dto.ConferenceSessionDTO;
import gts.spring.conferences.dto.AttendeeDTO;
import gts.spring.conferences.dto.PresenterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class ConferenceSessionControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DataSource dataSource;

    private String baseUrl;

    @BeforeEach
    void setup() throws Exception {
        baseUrl = "http://localhost:" + port + "/api/sessions";
        try (Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM conference_session_attendee");
            stmt.executeUpdate("DELETE FROM conference_session_presenter");
            stmt.executeUpdate("DELETE FROM conference_session");
            stmt.executeUpdate("DELETE FROM attendee");
            stmt.executeUpdate("DELETE FROM presenter");
        }
    }

    @Test
    void createAndGetSession() {
        ConferenceSessionDTO dto = new ConferenceSessionDTO();
        dto.setTitle("Spring Boot Deep Dive");
        dto.setStartTime(LocalDateTime.now());
        dto.setEndTime(LocalDateTime.now().plusHours(6));

        ResponseEntity<ConferenceSessionDTO> createResponse = restTemplate.postForEntity(baseUrl, dto, ConferenceSessionDTO.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ConferenceSessionDTO created = createResponse.getBody();
        assertThat(created).isNotNull();

        ResponseEntity<ConferenceSessionDTO> getResponse = restTemplate.getForEntity(baseUrl + "/" + created.getId(), ConferenceSessionDTO.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(getResponse.getBody()).getTitle()).isEqualTo("Spring Boot Deep Dive");
    }

    @Test
    void assignPresenterAndRegisterAttendee() {
        // Create session
        ConferenceSessionDTO session = new ConferenceSessionDTO();
        session.setTitle("Test Session");
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now().plusDays(4));
        session = restTemplate.postForEntity(baseUrl, session, ConferenceSessionDTO.class).getBody();

        // Create attendee
        AttendeeDTO attendee = new AttendeeDTO();
        attendee.setName("Test Attendee");
        attendee = restTemplate.postForEntity("http://localhost:" + port + "/api/attendees", attendee, AttendeeDTO.class).getBody();

        // Create presenter
        PresenterDTO presenter = new PresenterDTO();
        presenter.setName("Test Presenter");
        presenter = restTemplate.postForEntity("http://localhost:" + port + "/api/presenters", presenter, PresenterDTO.class).getBody();

        // Register attendee
        assert session != null;
        assert attendee != null;
        ResponseEntity<ConferenceSessionDTO> registerResponse = restTemplate.postForEntity(
                baseUrl + "/" + session.getId() + "/attendees/" + attendee.getId(),
                null, ConferenceSessionDTO.class);

        assertThat(registerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(registerResponse.getBody()).getAttendees()).hasSize(1);

        // Assign presenter
        assert presenter != null;
        ResponseEntity<ConferenceSessionDTO> assignResponse = restTemplate.postForEntity(
                baseUrl + "/" + session.getId() + "/presenters/" + presenter.getId(),
                null, ConferenceSessionDTO.class);

        assertThat(assignResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(assignResponse.getBody()).getPresenters()).hasSize(1);
    }

    @Test
    void getSessionsByAttendeeOrPresenter() {
        // Create session
        ConferenceSessionDTO session = new ConferenceSessionDTO();
        session.setTitle("Query Test");
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now().plusHours(8));
        session = restTemplate.postForEntity(baseUrl, session, ConferenceSessionDTO.class).getBody();

        // Create and register attendee
        AttendeeDTO attendee = new AttendeeDTO();
        attendee.setName("Query Attendee");
        attendee = restTemplate.postForEntity("http://localhost:" + port + "/api/attendees", attendee, AttendeeDTO.class).getBody();
        assert session != null;
        assert attendee != null;
        restTemplate.postForEntity(baseUrl + "/" + session.getId() + "/attendees/" + attendee.getId(), null, ConferenceSessionDTO.class);

        // Create and assign presenter
        PresenterDTO presenter = new PresenterDTO();
        presenter.setName("Query Presenter");
        presenter = restTemplate.postForEntity("http://localhost:" + port + "/api/presenters", presenter, PresenterDTO.class).getBody();
        assert presenter != null;
        restTemplate.postForEntity(baseUrl + "/" + session.getId() + "/presenters/" + presenter.getId(), null, ConferenceSessionDTO.class);

        // Get by attendee
        ResponseEntity<List<ConferenceSessionDTO>> attendeeResponse = restTemplate.exchange(
                baseUrl + "/attendee/" + attendee.getId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(attendeeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(attendeeResponse.getBody()).hasSize(1);

        // Get by presenter
        ResponseEntity<List<ConferenceSessionDTO>> presenterResponse = restTemplate.exchange(
                baseUrl + "/presenter/" + presenter.getId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(presenterResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(presenterResponse.getBody()).hasSize(1);
    }
}
