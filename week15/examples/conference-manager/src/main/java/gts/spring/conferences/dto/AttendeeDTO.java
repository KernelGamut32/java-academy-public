package gts.spring.conferences.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Attendee", description = "Details about an Attendee")
public class AttendeeDTO {

    @Schema(description = "The unique identifier of the attendee", example = "1")
    private Long id;

    @Schema(description = "The name of the attendee", example = "Jane Doe")
    @NotBlank
    private String name;

    @Schema(description = "The attendee's email address", example = "jane.doe@example.com")
    @Email
    private String email;
}
