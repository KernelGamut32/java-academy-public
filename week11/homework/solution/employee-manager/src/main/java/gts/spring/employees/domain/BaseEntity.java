package gts.spring.employees.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class BaseEntity {
    @EqualsAndHashCode.Include
    private Long id;
}
