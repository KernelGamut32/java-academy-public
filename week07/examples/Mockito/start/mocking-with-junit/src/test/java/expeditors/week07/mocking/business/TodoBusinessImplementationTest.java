package expeditors.week07.mocking.business;

import expeditors.week07.mocking.data.api.TodoService;
import expeditors.week07.mocking.data.stub.TodoServiceStub;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoBusinessImplementationTest {

    @Test
    public void usingAsStub() {
        TodoService todoService = new TodoServiceStub();
        TodoBusinessImplementation todoBusinessImplementation = new TodoBusinessImplementation(todoService);
        List<String> todos = todoBusinessImplementation.retrieveTodosRelatedToSpring("Allen");
        assertAll(() -> assertEquals(2, todos.size()),
                () -> assertEquals("Learn Spring MVC", todos.getFirst()),
                () -> assertEquals("Learn Spring", todos.get(1)));
    }

}