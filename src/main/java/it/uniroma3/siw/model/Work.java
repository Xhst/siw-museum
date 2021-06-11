package it.uniroma3.siw.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
@Table(name = "works")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageUrl;

    @Column(length = 5000)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfRealization;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Collection collection;
}
