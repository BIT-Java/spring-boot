package pl.mzlnk.bitjava.springbootrestiapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mzlnk.bitjava.springbootrestiapi.model.entity.Entity;
import pl.mzlnk.bitjava.springbootrestiapi.repository.EntityRepository;
import pl.mzlnk.bitjava.springbootrestiapi.repository.impl.EntityRepositoryImpl;
import pl.mzlnk.bitjava.springbootrestiapi.service.EntityService;

import java.util.List;
import java.util.Optional;

@Service
public class EntityServiceImpl implements EntityService {

    @Autowired
    private EntityRepositoryImpl entityRepository;

    @Override
    public List<Entity> findAll() {
        return entityRepository.findAll();
    }

    @Override
    public Optional<Entity> findById(String id) {
        return entityRepository.findById(id);
    }

    @Override
    public Optional<Entity> findByNameAndSurname(String name, String surname) {
        return entityRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public void remove(String uuid) {
        entityRepository.remove(uuid);
    }

    @Override
    public void createOrUpdate(Entity entity) {
        entityRepository.createOrUpdate(entity);
    }
}
