package it.uniroma3.siw.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private LocalDate dateOfBirth;
    private String birthPlace;

    private LocalDate dateOfDeath;
    private String placeOfDeath;

    private String nationality;

    @Column(length = 5000)
    private String biography;

    /**
     * Opere realizzate dall'artista.
     */
    @OneToMany(mappedBy = "artist")
    private List<Work> works;


    public Artist() {
        this.works = new ArrayList<>();
    }

    public Artist(String firstName, String lastName) {
        this();

        this.firstName = firstName;
        this.lastName = lastName;
    }
}
