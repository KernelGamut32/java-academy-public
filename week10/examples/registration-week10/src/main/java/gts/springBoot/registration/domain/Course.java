package gts.springBoot.registration.domain;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Course {

    private int id;
    private String title;
    private String code;
    private double credits = 2.5;

    public Course() {
    }

    public Course(String code, String title) {
        super();
        this.title = title;
        this.code = code;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return Objects.equals(title, course.title) && Objects.equals(code, course.code);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", title='" + getTitle() + "'" +
                ", code='" + getCode() + "'" +
                ", credits=" + getCredits() +
                '}';
    }
}
