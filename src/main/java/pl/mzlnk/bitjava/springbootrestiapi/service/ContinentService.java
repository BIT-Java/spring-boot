package pl.mzlnk.bitjava.springbootrestiapi.service;

import pl.mzlnk.bitjava.springbootrestiapi.entity.Continent;

import java.util.List;
import java.util.Optional;

public interface ContinentService {

    List<Continent> findAll();
    Optional<Continent> findById(int id);
    void createOrUpdate(Continent continent);

}
