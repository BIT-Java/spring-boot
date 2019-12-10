package pl.mzlnk.bitjava.springbootrestiapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Entity {

    private String id;
    private String name;
    private String surname;

}
