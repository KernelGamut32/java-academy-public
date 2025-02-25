package gts.spring.registration.dao.inmemory;

import gts.spring.registration.dao.BaseDAO;
import gts.spring.registration.domain.ScheduledClass;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryScheduledClassDAO implements BaseDAO<ScheduledClass> {

    private Map<Long, ScheduledClass> classes = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    @Override
    public boolean update(ScheduledClass scheduledClass) {
        return classes.computeIfPresent(scheduledClass.getId(),
                (key, oldValue) -> scheduledClass) != null;
    }

    @Override
    public boolean delete(ScheduledClass scheduledClass) {
        return classes.remove(scheduledClass.getId()) != null;
    }

    @Override
    public ScheduledClass insert(ScheduledClass scheduledClass) {
        Long newId = nextId.getAndIncrement();
        scheduledClass.setId(newId);
        classes.put(newId, scheduledClass);

        return scheduledClass;
    }

    @Override
    public Optional<ScheduledClass> findById(Long id) {
        return Optional.ofNullable(classes.get(id));
    }

    @Override
    public List<ScheduledClass> findAll() {
        return new ArrayList<>(classes.values());
    }

    @Override
    public void resetDataStore() {
        classes = new ConcurrentHashMap<>();
        nextId = new AtomicLong(1);
    }
}
