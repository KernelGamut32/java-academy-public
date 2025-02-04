package ttl.larku.dao;

import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public interface BaseDAO<T> {

    boolean update(T updateObject);
    boolean delete(T deleteObject);
    T insert(T newObject);
    T findById(int id);
    List<T> findAll();
    void deleteStore();
    void createStore();
    default List<T> findBy(Predicate<T> pred) {
        return findAll().stream()
                .filter(pred)
                .collect(toList());
    }
}
