package gts.spring.registration.domain;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
public class Course extends BaseEntity {

    private String title;
    private String code;
    private double credits;
}
