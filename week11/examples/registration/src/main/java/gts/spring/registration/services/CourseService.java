package gts.spring.registration.services;

import gts.spring.registration.dao.BaseDAO;
import gts.spring.registration.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final BaseDAO<Course> courseDAO;

    public Course createCourse(Course course) {
        return courseDAO.insert(course);
    }

    public boolean deleteCourse(Long id) {
        Optional<Course> course = courseDAO.findById(id);
        return course.filter(courseDAO::delete).isPresent();
    }

    public boolean updateCourse(Course course) {
        Optional<Course> existingCourse = courseDAO.findById(course.getId());
        return existingCourse.isPresent() && existingCourse
                .map(c -> {
                    c.setTitle(course.getTitle());
                    c.setCode(course.getCode());
                    c.setCredits(course.getCredits());
                    return courseDAO.update(c);
                })
                .orElse(false);
    }

    public Course getCourseByCode(String code) {
        List<Course> courses = courseDAO.findBy(c ->
                c.getCode().equalsIgnoreCase(code));
        return courses.isEmpty() ? null : courses.getFirst();
    }

    public List<Course> getCoursesByCode(String code) {
        return courseDAO.findBy(c ->
                c.getCode().toLowerCase().contains(code.toLowerCase()));
    }

    public Course getCourseById(Long id) {
        return courseDAO.findById(id).orElse(null);
    }

    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }
}
