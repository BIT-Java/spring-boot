package pl.mzlnk.bitjava.springbootrestiapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mzlnk.bitjava.springbootrestiapi.entity.Continent;
import pl.mzlnk.bitjava.springbootrestiapi.repository.ContinentRepository;
import pl.mzlnk.bitjava.springbootrestiapi.service.ContinentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    private ContinentRepository continentRepository;

    @Override
    public List<Continent> findAll() {
        List<Continent> list = new ArrayList<>();
        continentRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Optional<Continent> findById(int id) {
        return continentRepository.findById(id);
    }

    @Override
    public void createOrUpdate(Continent continent) {
        continentRepository.save(continent);
    }

}
