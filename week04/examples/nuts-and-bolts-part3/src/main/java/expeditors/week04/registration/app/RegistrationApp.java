package expeditors.week04.registration.app;

import expeditors.week04.registration.domain.Course;
import expeditors.week04.registration.service.CourseService;

import java.util.List;

public class RegistrationApp {
    private CourseService courseService;

    public static void main(String[] args) {
        RegistrationApp ra = new RegistrationApp();
        ra.init();
        ra.postRequestToAddACourse();
        ra.getRequestForAllCourses();
    }

    private void postRequestToAddACourse() {
        courseService.createCourse("Intro to Programming", "CS-101",
                "Provides an introductory overview of programming");
    }

    private void getRequestForAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        System.out.printf("All %d Courses:\n", courses.size());
        courses.forEach(System.out::println);
    }

    private void init() {
        courseService = new CourseService();
        courseService.createCourse("Intro to Math", "Math-101",
                "Provides an introductory overview of mathematics");
        courseService.createCourse("Intermediate Math", "Math-201",
                "Provides an intermediate exploration of mathematics");
        courseService.createCourse("Intro to Physics", "Phys-101",
                "Provides an introductory overview of physics");
    }
}
