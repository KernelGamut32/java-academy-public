package expeditors.week07.mocking.business;

import expeditors.week07.mocking.data.api.TodoService;
import expeditors.week07.mocking.data.stub.TodoServiceStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoBusinessImplementationTest {

    @Mock
    private TodoService todoServiceMock;

    @InjectMocks
    private TodoBusinessImplementation todoBusinessImplementation;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

//    @BeforeEach
//    public void setUp() {
//        todoServiceMock = mock(TodoService.class);
//        todoBusinessImplementation = new TodoBusinessImplementation(todoServiceMock);
//    }

    @Test
    public void usingAsStub() {
        TodoService todoService = new TodoServiceStub();
        TodoBusinessImplementation todoBusinessImplementation = new TodoBusinessImplementation(todoService);
        List<String> todos = todoBusinessImplementation.retrieveTodosRelatedToSpring("Allen");
        assertAll(() -> assertEquals(2, todos.size()),
                () -> assertEquals("Learn Spring MVC", todos.getFirst()),
                () -> assertEquals("Learn Spring", todos.get(1)));
    }

    @Test
    public void usingMockito() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Bounce", "Learn to Code");
        when(todoServiceMock.retrieveTodos("Allen")).thenReturn(allTodos);
        List<String> todos = todoBusinessImplementation.retrieveTodosRelatedToSpring("Allen");
        assertAll(() -> assertEquals(1, todos.size()),
                () -> assertEquals("Learn Spring MVC", todos.getFirst()));
    }

    @Test
    public void usingMockitoEmptyResults() {
        when(todoServiceMock.retrieveTodos("Allen")).thenReturn(new ArrayList<>());
        List<String> todos = todoBusinessImplementation.retrieveTodosRelatedToSpring("Allen");
        assertEquals(0, todos.size());
    }

    @Test
    public void usingMockitoTestDelete() {
        // Arrange
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Bounce", "Learn to Code");
        when(todoServiceMock.retrieveTodos("Allen")).thenReturn(allTodos);

        // Act
        todoBusinessImplementation.deleteTodosNotRelatedToSpring("Allen");

        // Assert
        verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
        verify(todoServiceMock).deleteTodo("Learn Bounce");
        verify(todoServiceMock).deleteTodo("Learn to Code");
        verify(todoServiceMock, times(1)).deleteTodo("Learn Bounce");
        verify(todoServiceMock, times(2)).deleteTodo(anyString());
        verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Code");
        verify(todoServiceMock, atLeast(1)).deleteTodo(anyString());
    }

//    @Test
//    public void usingMockitoCaptureArguments() {
//        // Arrange
//        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
//        List<String> allTodos = Arrays.asList("Learn Spring MVC",
//                "Learn Bounce", "Learn to Code");
//        when(todoServiceMock.retrieveTodos("Allen")).thenReturn(allTodos);
//
//        // Act
//        todoBusinessImplementation.deleteTodosNotRelatedToSpring("Allen");
//
//        // Assert
//        verify(todoServiceMock, times(2)).deleteTodo(argumentCaptor.capture());
//        var argValues = argumentCaptor.getAllValues();
//        assertAll(() -> assertEquals(2, argValues.size()),
//                () -> assertTrue(argValues.contains("Learn Bounce")),
//                () -> assertTrue(argValues.contains("Learn to Code")));
//    }

    @Test
    public void usingMockitoCaptureArguments() {
        // Arrange
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Bounce", "Learn to Code");
        when(todoServiceMock.retrieveTodos("Allen")).thenReturn(allTodos);

        // Act
        todoBusinessImplementation.deleteTodosNotRelatedToSpring("Allen");

        // Assert
        verify(todoServiceMock, times(2)).deleteTodo(stringArgumentCaptor.capture());
        var argValues = stringArgumentCaptor.getAllValues();
        assertAll(() -> assertEquals(2, argValues.size()),
                () -> assertTrue(argValues.contains("Learn Bounce")),
                () -> assertTrue(argValues.contains("Learn to Code")));
    }

}