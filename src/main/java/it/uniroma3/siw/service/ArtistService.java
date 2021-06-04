package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.repository.ArtistRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;


    public Artist getArtistById(Long id) {
        return this.artistRepository.findById(id)
                .orElse(null);
    }

    public Artist getArtistFromFullName(String firstName, String lastName) {
        return this.artistRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElse(null);
    }

    public List<Artist> getAll() {
        return this.artistRepository.findAll();
    }

    public Artist save(Artist artist) {
        return this.artistRepository.save(artist);
    }

}
