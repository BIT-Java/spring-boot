package pl.mzlnk.bitjava.springbootrestiapi.repository.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.mzlnk.bitjava.springbootrestiapi.model.entity.Entity;
import pl.mzlnk.bitjava.springbootrestiapi.repository.EntityRepository;

import java.util.*;

@Repository
public class EntityRepositoryImpl implements EntityRepository {

    private Map<String, Entity> entities = new HashMap<>();

    public EntityRepositoryImpl() {
        entities.put("a1", new Entity("a1", "John", "Smiths"));
        entities.put("a2", new Entity("a2", "Emily", "Smiths"));
        entities.put("a3", new Entity("a3", "John", "Brown"));
        entities.put("a4", new Entity("a4", "Adam", "Johnson"));
    }

    @Override
    public List<Entity> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public Optional<Entity> findById(String id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Optional<Entity> findByNameAndSurname(String name, String surname) {
        return entities
                .values()
                .stream()
                .filter(e -> e.getName().equals(name))
                .filter(e -> e.getSurname().equals(surname))
                .findFirst();
    }

    @Override
    public void remove(String id) {
        entities.remove(id);
    }

    @Override
    public void createOrUpdate(Entity entity) {
        entities.put(entity.getId(), entity);
    }

}
