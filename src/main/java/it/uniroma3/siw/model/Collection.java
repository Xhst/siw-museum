package it.uniroma3.siw.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "works_collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToOne
    private User curator;

    @OneToMany(mappedBy = "collection")
    private List<Work> works;


    public Collection() {
        this.works = new ArrayList<>();
    }

    public Collection(String name, User curator) {
        this();

        this.name = name;
        this.curator = curator;
    }
}
