package pl.mzlnk.bitjava.springbootrestiapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mzlnk.bitjava.springbootrestiapi.entity.Continent;
import pl.mzlnk.bitjava.springbootrestiapi.service.ContinentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/continent")
public class ContinentController {

    @Autowired
    private ContinentService service;

    @GetMapping("/all")
    public List<Continent> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Continent> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public String createOrUpdate(@RequestBody Continent continent) {
        try {
            service.createOrUpdate(continent);
            return "done";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}
