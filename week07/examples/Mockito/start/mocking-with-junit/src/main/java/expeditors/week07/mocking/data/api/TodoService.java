package expeditors.week07.mocking.data.api;

import java.util.List;

public interface TodoService {

    List<String> retrieveTodos(String user);

}