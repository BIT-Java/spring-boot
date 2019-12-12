package pl.mzlnk.bitjava.springbootrestiapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mzlnk.bitjava.springbootrestiapi.model.entity.Entity;
import pl.mzlnk.bitjava.springbootrestiapi.service.EntityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entity")
public class EntityController {

    @Autowired
    private EntityService entityService;

    @GetMapping("/all")
    public List<Entity> findAll() {
        return entityService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Entity> findById(@PathVariable String id) {
        return entityService.findById(id);
    }

    @PostMapping("/{id}")
    public void addEntity(@RequestParam String id,
                          @RequestParam String name,
                          @RequestParam String surname) {
        entityService.createOrUpdate(new Entity(id, name, surname));
    }


}
