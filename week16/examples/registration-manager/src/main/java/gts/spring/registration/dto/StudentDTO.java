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

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Schema(name = "Student", description = "Details about a Student")
public class StudentDTO extends BaseDTO {

    @Schema(description = "The first name of the student", example = "Melissa")
    @NotBlank
    private String firstName;

    @Schema(description = "The last name of the student", example = "Testing")
    @NotBlank
    private String lastName;

    @Schema(description = "Student phone number", example = "800-555-1111")
    private String phoneNumber;

    @Schema(description = "Student date of birth", example = "1980-03-01")
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

    @Schema(description = "Student status", example = "FULL_TIME")
    @NotBlank
    private String status;
}