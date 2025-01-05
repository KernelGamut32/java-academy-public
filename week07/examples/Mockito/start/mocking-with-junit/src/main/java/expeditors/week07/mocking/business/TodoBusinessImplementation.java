package expeditors.week07.mocking.business;

import expeditors.week07.mocking.data.api.TodoService;

import java.util.List;

public class TodoBusinessImplementation {

    private final TodoService todoService;

    TodoBusinessImplementation(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user) {
        List<String> allTodos = todoService.retrieveTodos(user);
        return allTodos.stream()
                .filter(t -> t.contains("Spring"))
                .toList();
    }

}