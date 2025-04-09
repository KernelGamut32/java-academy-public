package gts.spring.registration.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Schema(name = "Course", description = "Details about a Course")
public class CourseDTO extends BaseDTO {

    @Schema(description = "The title of the course", example = "Introduction to Java")
    @NotBlank
    private String title;

    @Schema(description = "The code associated to the course", example = "J0001")
    @NotBlank
    private String code;

    @Schema(description = "Credits available with the course", example = "10.5")
    @DecimalMin(value = "0.5")
    @DecimalMax(value = "25.0")
    private double credits;
}
