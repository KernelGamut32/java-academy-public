package gts.spring.employees.dao.inmemory;

import gts.spring.employees.dao.BaseDAO;
import gts.spring.employees.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class InMemoryEmployeeDAO<T extends BaseEntity> implements BaseDAO<T> {
    protected Map<Long, T> datastore = new ConcurrentHashMap<>();
    protected AtomicLong nextId = new AtomicLong(1);

    @Override
    public boolean update(T entity) {
        return datastore.computeIfPresent(entity.getId(), (key, oldValue) -> entity) != null;
    }

    @Override
    public boolean delete(T entity) {
        return datastore.remove(entity.getId()) != null;
    }

    @Override
    public T insert(T entity) {
        Long newId = nextId.getAndIncrement();
        entity.setId(newId);
        datastore.put(newId, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(datastore.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(datastore.values());
    }

    @Override
    public void resetDataStore() {
        datastore = new ConcurrentHashMap<>();
        nextId = new AtomicLong(1);
    }
}