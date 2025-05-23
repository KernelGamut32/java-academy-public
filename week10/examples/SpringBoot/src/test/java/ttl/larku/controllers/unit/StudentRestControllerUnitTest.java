package ttl.larku.controllers.unit;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import ttl.larku.controllers.rest.RestResultWrapper;
import ttl.larku.controllers.rest.RestResultWrapper.Status;
import ttl.larku.controllers.rest.StudentRestController;
import ttl.larku.controllers.rest.UriCreator;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class StudentRestControllerUnitTest {

   @InjectMocks
   private StudentRestController controller;

   @Mock
   private StudentService studentService;

   @Mock
   private UriCreator uriCreator;


   List<Student> students = Arrays.asList(
         new Student("Manoj", "282 929 9292", Student.Status.FULL_TIME),
         new Student("Alice", "393 9393 030", Student.Status.HIBERNATING));

   private final int goodStudentId = 1;
   private final int badStudentId = 10000;

   @Test
   public void testGetOneStudentGoodJson() throws Exception {
      Mockito.when(studentService.getStudent(goodStudentId)).thenReturn(students.get(0));
      ResponseEntity<?> response = controller.getStudent(goodStudentId);

      assertEquals(200, response.getStatusCodeValue());
      @SuppressWarnings({"unchecked", "rawtypes"})
      RestResultWrapper<Student> result = (RestResultWrapper) response.getBody();
      assertEquals(Status.Ok, result.getStatus());

      Mockito.verify(studentService).getStudent(goodStudentId);
   }

   @Test
   public void testGetOneStudentBadId() throws Exception {
      Mockito.when(studentService.getStudent(badStudentId)).thenReturn(null);
      var status = HttpStatus.BAD_REQUEST;
      var message = "Student with id: " + badStudentId + " not found";

      var pd = ProblemDetail.forStatusAndDetail(status, message);
      Mockito.when(uriCreator.getProblemDetail(status, message)).thenReturn(pd);

      ResponseEntity<?> response = controller.getStudent(badStudentId);
      assertEquals(400, response.getStatusCodeValue());
      @SuppressWarnings({"unchecked", "rawtypes"})
      RestResultWrapper<Student> result = (RestResultWrapper) response.getBody();
      assertEquals(Status.Error, result.getStatus());

      Mockito.verify(uriCreator).getProblemDetail(status, message);
      Mockito.verify(studentService).getStudent(badStudentId);
   }

   @Test
   public void testAddStudentGood() throws Exception {
      Student student = new Student("Yogita");
      student.setId(1);

      URI newStudentURI = new URI("http://localhost:8080/adminrest/student/1");

      Mockito.when(studentService.createStudent(student)).thenReturn(student);
      Mockito.when(uriCreator.getURI(1)).thenReturn(newStudentURI);

      ResponseEntity<?> response = controller.createStudent(student);
      assertEquals(201, response.getStatusCodeValue());
      assertEquals(newStudentURI, response.getHeaders().getLocation());

      @SuppressWarnings({"unchecked", "rawtypes"})
      RestResultWrapper<Student> result = (RestResultWrapper) response.getBody();

      assertEquals(Status.Ok, result.getStatus());
      assertEquals(1, result.getEntity().getId());


      Mockito.verify(studentService).createStudent(student);
      Mockito.verify(uriCreator).getURI(1);

   }

   @Test
   public void testGetAllStudentsGood() throws Exception {
      Mockito.when(studentService.getAllStudents()).thenReturn(students);

      ResponseEntity<?> response = controller.getAllStudents();
      assertEquals(200, response.getStatusCodeValue());
      @SuppressWarnings({"unchecked", "rawtypes"})
      RestResultWrapper<List<Student>> result = (RestResultWrapper) response.getBody();
      assertEquals(Status.Ok, result.getStatus());

      assertEquals(2, result.getEntity().size());

      Mockito.verify(studentService).getAllStudents();
   }

   @Test
   public void testUpdateStudentGood() throws Exception {
      Student s = students.get(0);
      s.setId(goodStudentId);

      Mockito.when(studentService.updateStudent(s)).thenReturn(true);

      ResponseEntity<?> response = controller.updateStudent(s);
      assertEquals(204, response.getStatusCodeValue());

      Mockito.verify(studentService).updateStudent(s);
   }

   @Test
   public void testUpdateStudentBad() throws Exception {
      Student s = students.get(0);
      s.setId(badStudentId);

      Mockito.when(studentService.updateStudent(s)).thenReturn(false);

      ResponseEntity<?> response = controller.updateStudent(s);
      assertEquals(400, response.getStatusCodeValue());

      Mockito.verify(studentService).updateStudent(s);

   }

   @Test
   public void testDeleteStudentGood() throws Exception {

      Mockito.when(studentService.deleteStudent(goodStudentId)).thenReturn(true);

      ResponseEntity<?> response = controller.deleteStudent(goodStudentId);
      assertEquals(204, response.getStatusCodeValue());

      Mockito.verify(studentService).deleteStudent(goodStudentId);
   }

   @Test
   public void testDeleteStudentBad() throws Exception {
      Mockito.when(studentService.deleteStudent(badStudentId)).thenReturn(false);

      ResponseEntity<?> response = controller.deleteStudent(badStudentId);
      assertEquals(400, response.getStatusCodeValue());

      Mockito.verify(studentService).deleteStudent(badStudentId);
   }

}
