package pl.mzlnk.bitjava.springbootrestiapi.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "continent")
public class Continent {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    private ContinentCode code;

    @Column(name = "name")
    private String name;

}
