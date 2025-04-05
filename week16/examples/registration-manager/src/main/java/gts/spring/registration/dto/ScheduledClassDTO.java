package gts.spring.registration.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Schema(name = "Scheduled Class", description = "Details about a Scheduled Class")
public class ScheduledClassDTO extends BaseDTO {

    @Schema(description = "The section name for the scheduled class", example = "001A")
    @NotBlank
    private String sectionName;

    @Schema(description = "Start date for the scheduled class", example = "2025-04-01")
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @Schema(description = "End date for the scheduled class", example = "2025-04-30")
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;

    @Schema(description = "Course associated to the scheduled class")
    @NotNull(message = "A course must be provided for a scheduled class")
    private CourseDTO course;

    @Schema(description = "Students assigned to the scheduled class")
    private List<StudentDTO> students;
}
