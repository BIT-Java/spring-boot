package pl.mzlnk.bitjava.springbootrestiapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.mzlnk.bitjava.springbootrestiapi.entity.Continent;

public interface ContinentRepository extends CrudRepository<Continent, Integer> {

    @Query("SELECT * FROM continents where population >= :population")
    Continent findAllByMinPopulation(int population);

}
