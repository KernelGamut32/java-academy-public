package ttl.larku.dao.inmemory;

import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.ScheduledClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryClassDAO implements BaseDAO<ScheduledClass> {

    private Map<Integer, ScheduledClass> classes = new ConcurrentHashMap<>();
    private AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public boolean update(ScheduledClass updateObject) {
        return classes.computeIfPresent(updateObject.getId(), (key, oldValue) -> updateObject) != null;
    }

    @Override
    public boolean delete(ScheduledClass sc) {
        return classes.remove(sc.getId()) != null;
    }

    @Override
    public ScheduledClass insert(ScheduledClass newObject) {
        int newId = nextId.getAndIncrement();
        newObject.setId(newId);
        classes.put(newId, newObject);

        return newObject;
    }

    @Override
    public ScheduledClass findById(int id) {
        return classes.get(id);
    }

    @Override
    public List<ScheduledClass> findAll() {
        return new ArrayList<>(classes.values());
    }

    @Override
    public void deleteStore() {
        classes = null;
    }

    @Override
    public void createStore() {
        classes = new ConcurrentHashMap<>();
        nextId = new AtomicInteger(1);
    }

}
