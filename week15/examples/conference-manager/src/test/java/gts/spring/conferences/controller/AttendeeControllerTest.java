package gts.spring.conferences.controller;

import gts.spring.conferences.dto.AttendeeDTO;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class AttendeeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DataSource dataSource;

    private String baseUrl;

    @BeforeEach
    void setup() throws SQLException {
        baseUrl = "http://localhost:" + port + "/api/attendees";

        // Clean up attendee/session join table if needed
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            // Clear join table first to avoid FK violations
            stmt.executeUpdate("DELETE FROM conference_session_attendee");
            stmt.executeUpdate("DELETE FROM attendee");
        }
    }

    @Test
    void createAndGetAttendee() {
        AttendeeDTO dto = new AttendeeDTO();
        dto.setName("Alice Johnson");

        ResponseEntity<AttendeeDTO> createResponse = restTemplate.postForEntity(baseUrl, dto, AttendeeDTO.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        AttendeeDTO created = createResponse.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getName()).isEqualTo("Alice Johnson");

        ResponseEntity<AttendeeDTO> getResponse = restTemplate.getForEntity(baseUrl + "/" + created.getId(), AttendeeDTO.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(getResponse.getBody()).getName()).isEqualTo("Alice Johnson");
    }

    @Test
    void getAllAttendees_ShouldReturnList() {
        AttendeeDTO dto1 = new AttendeeDTO();
        dto1.setName("John Doe");

        restTemplate.postForEntity(baseUrl, dto1, AttendeeDTO.class);

        ResponseEntity<List<AttendeeDTO>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void updateAttendee_ShouldModifyExisting() {
        AttendeeDTO dto = new AttendeeDTO();
        dto.setName("Jane Smith");

        AttendeeDTO created = restTemplate.postForEntity(baseUrl, dto, AttendeeDTO.class).getBody();
        assert created != null;
        created.setName("Jane Doe");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AttendeeDTO> entity = new HttpEntity<>(created, headers);

        ResponseEntity<AttendeeDTO> updateResponse = restTemplate.exchange(
                baseUrl + "/" + created.getId(),
                HttpMethod.PUT,
                entity,
                AttendeeDTO.class
        );

        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(updateResponse.getBody()).getName()).isEqualTo("Jane Doe");
    }

    @Test
    void deleteAttendee_ShouldReturnNoContent() {
        AttendeeDTO dto = new AttendeeDTO();
        dto.setName("Bob Brown");

        AttendeeDTO created = restTemplate.postForEntity(baseUrl, dto, AttendeeDTO.class).getBody();

        assert created != null;
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                baseUrl + "/" + created.getId(),
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<AttendeeDTO> getAfterDelete = restTemplate.getForEntity(baseUrl + "/" + created.getId(), AttendeeDTO.class);
        assertThat(getAfterDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
