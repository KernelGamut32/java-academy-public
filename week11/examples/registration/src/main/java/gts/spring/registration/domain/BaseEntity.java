package gts.spring.registration.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class BaseEntity {
    private Long id;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof BaseEntity other) {
            return this.id != null && Objects.equals(this.id, other.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
