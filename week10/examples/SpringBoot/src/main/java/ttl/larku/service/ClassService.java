package ttl.larku.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Course;
import ttl.larku.domain.ScheduledClass;

public class ClassService {

   private CourseService courseService;
   private BaseDAO<ScheduledClass> classDAO;


   public ScheduledClass createScheduledClass(String courseCode, LocalDate startDate, LocalDate endDate) {
      Course course = courseService.getCourseByCode(courseCode);
      if (course != null) {
         ScheduledClass sClass = new ScheduledClass(course, startDate, endDate);
         sClass = classDAO.insert(sClass);
         return sClass;
      }
      return null;
   }

   public boolean deleteScheduledClass(int id) {
      ScheduledClass sc = classDAO.findById(id);
      if (sc != null) {
         classDAO.delete(sc);
         return true;
      }
      return false;
   }

   public boolean updateScheduledClass(ScheduledClass newClass) {
      ScheduledClass oldClass = classDAO.findById(newClass.getId());
      if (oldClass != null) {
         classDAO.update(newClass);
         return true;
      }
      return false;
   }

   public List<ScheduledClass> getScheduledClasses(String code, LocalDate startDate, LocalDate endDate) {
      List<ScheduledClass> result = classDAO.findAll().stream()
            .filter(sc -> sc.getCourse().getCode().equals(code)
                  && sc.getStartDate().equals(startDate)
                  && sc.getEndDate().equals(endDate))
            .collect(Collectors.toList());

      return result;
   }

   public List<ScheduledClass> getScheduledClassesByCourseCode(String code) {
      List<ScheduledClass> result = classDAO.findAll().stream()
            .filter(sc -> sc.getCourse().getCode().equals(code))
            .collect(Collectors.toList());

      return result;
   }


   public ScheduledClass getScheduledClass(int id) {
      return classDAO.findById(id);
   }

   public List<ScheduledClass> getAllScheduledClasses() {
      return classDAO.findAll();
   }

   public BaseDAO<ScheduledClass> getClassDAO() {
      return classDAO;
   }

   public void setClassDAO(BaseDAO<ScheduledClass> classDAO) {
      this.classDAO = classDAO;
   }

   public CourseService getCourseService() {
      return courseService;
   }

   public void setCourseService(CourseService courseService) {
      this.courseService = courseService;
   }

   public void clear() {
      classDAO.deleteStore();
      classDAO.createStore();
   }
}
