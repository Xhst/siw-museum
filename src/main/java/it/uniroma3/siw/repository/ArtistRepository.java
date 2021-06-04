package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Artist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    List<Artist> findAll();

    Optional<Artist> findByFirstNameAndLastName(String firstName, String lastName);
}
