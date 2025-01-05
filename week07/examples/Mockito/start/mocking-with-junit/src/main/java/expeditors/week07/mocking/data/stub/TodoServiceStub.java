package expeditors.week07.mocking.data.stub;

import expeditors.week07.mocking.data.api.TodoService;

import java.util.Arrays;
import java.util.List;

// Issues with "stubs":
//      - For additional test cases (e.g., test return of 0 results), stub framework grows in complexity:
//          * Either add different stub that implements same interface to cover alternate cases
//          * Or use convoluted if logic in stub to vary return results by something like user
//      - If interface is modified, stub now no longer compiles - requires additional implementation manually
//
// With a mocking framework (like Mockito) we'll be able to allow the framework to handle many
// of these concerns for us automatically or inline (with much less code).

public class TodoServiceStub implements TodoService {

    @Override
    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
    }

}
