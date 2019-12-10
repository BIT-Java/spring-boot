package pl.mzlnk.bitjava.springbootrestiapi.service;

import pl.mzlnk.bitjava.springbootrestiapi.model.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityService {

    List<Entity> findAll();
    Optional<Entity> findById(String id);
    Optional<Entity> findByNameAndSurname(String name, String surname);
    void remove(String uuid);
    void createOrUpdate(Entity entity);

}
