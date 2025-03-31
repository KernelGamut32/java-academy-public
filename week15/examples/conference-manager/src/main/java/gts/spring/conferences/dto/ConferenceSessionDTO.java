package gts.spring.conferences.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Schema(name = "ConferenceSession", description = "Details about a Conference Session")
public class ConferenceSessionDTO {

    @Schema(description = "The unique identifier of the session", example = "1")
    private Long id;

    @Schema(description = "The title of the session", example = "Spring Boot Deep Dive")
    @NotBlank
    private String title;

    @Schema(description = "The description of the session", example = "Exploring advanced Spring Boot topics")
    private String description;

    @Schema(description = "Session start time (ISO 8601)", example = "2025-04-01T10:00:00")
    @NotNull
    private LocalDateTime startTime;

    @Schema(description = "Session end time (ISO 8601)", example = "2025-04-01T12:00:00")
    @NotNull
    private LocalDateTime endTime;

    @Schema(description = "List of associated presenters")
    private List<PresenterDTO> presenters;

    @Schema(description = "List of attendees")
    private List<AttendeeDTO> attendees;
}
