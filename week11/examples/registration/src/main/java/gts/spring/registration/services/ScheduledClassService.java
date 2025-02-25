package gts.spring.registration.services;

import gts.spring.registration.dao.BaseDAO;
import gts.spring.registration.domain.Course;
import gts.spring.registration.domain.ScheduledClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduledClassService {

    private final CourseService courseService;
    private final BaseDAO<ScheduledClass> scheduledClassDAO;

    public ScheduledClass createScheduledClass(String courseCode, LocalDate startDate, LocalDate endDate) {
        Course course = courseService.getCourseByCode(courseCode);
        if (course != null) {
            ScheduledClass scheduledClass = ScheduledClass.builder()
                    .startDate(startDate)
                    .endDate(endDate)
                    .course(course).build();
            return scheduledClassDAO.insert(scheduledClass);
        }
        return null;
    }

    public void deleteScheduledClass(Long id) {
        Optional<ScheduledClass> scheduledClass = scheduledClassDAO.findById(id);
        scheduledClass.filter(scheduledClassDAO::delete);
    }

    public boolean updateScheduledClass(ScheduledClass scheduledClass) {
        return scheduledClassDAO.findById(scheduledClass.getId())
                .map(sc -> {
                    sc.setCourse(scheduledClass.getCourse());
                    sc.setStartDate(scheduledClass.getStartDate());
                    sc.setEndDate(scheduledClass.getEndDate());
                    return scheduledClassDAO.update(sc);
                })
                .orElse(false);
    }

    public List<ScheduledClass> getScheduledClasses(String code, LocalDate startDate, LocalDate endDate) {
        return scheduledClassDAO.findAll().stream()
                .filter(sc -> sc.getCourse().getCode().equalsIgnoreCase(code)
                        && sc.getStartDate().equals(startDate)
                        && sc.getEndDate().equals(endDate))
                .collect(Collectors.toList());
    }

    public List<ScheduledClass> getScheduledClassesByCourseCode(String code) {
        return scheduledClassDAO.findAll().stream()
                .filter(sc -> sc.getCourse().getCode().equalsIgnoreCase(code))
                .collect(Collectors.toList());
    }

    public ScheduledClass getScheduledClassById(Long id) {
        return scheduledClassDAO.findById(id).orElse(null);
    }

    public List<ScheduledClass> getAllScheduledClasses() {
        return scheduledClassDAO.findAll();
    }
}
