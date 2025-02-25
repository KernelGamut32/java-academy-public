package gts.spring.registration.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public interface BaseDAO<T> {

    boolean update(T updateObject);
    boolean delete(T deleteObject);
    T insert(T newObject);
    Optional<T> findById(Long id);
    List<T> findAll();
    void resetDataStore();
    default List<T> findBy(Predicate<T> pred) {
        return findAll().stream()
                .filter(pred)
                .collect(toList());
    }
}
