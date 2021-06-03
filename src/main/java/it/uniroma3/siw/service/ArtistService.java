package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.repository.ArtistRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Transactional
    public Artist getArtistById(Long id) {
        return this.artistRepository.findById(id)
                .orElse(null);
    }

    @Transactional
    public List<Artist> getAll() {
        return this.artistRepository.findAll();
    }

}
