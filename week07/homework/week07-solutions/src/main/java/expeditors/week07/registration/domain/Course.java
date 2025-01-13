package expeditors.week07.registration.domain;

import java.util.Objects;

public class Course {

    private int id;
    private final String title;
    private final String code;
    private final String description;

    public Course(String title, String code, String description) {
        super();
        this.title = title;
        this.code = code;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", getTitle(), getCode(), getDescription());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return Objects.equals(title, course.title) && Objects.equals(code, course.code) &&
                Objects.equals(description, course.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, code, description);
    }

}
