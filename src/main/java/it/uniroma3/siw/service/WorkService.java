package it.uniroma3.siw.service;

import it.uniroma3.siw.dto.WorkDto;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Work;
import it.uniroma3.siw.model.Collection;
import it.uniroma3.siw.repository.WorkRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;

    private final ArtistService artistService;
    private final CollectionService collectionService;


    public Work getById(Long id) {
        return this.workRepository.findById(id)
                .orElse(null);
    }

    public void deleteById(Long id) {
        Work work = this.getById(id);

        if(work == null) return;

        this.workRepository.deleteById(id);
    }

    public List<Work> getAll() {
        return this.workRepository.findAll();
    }

    public Work save(Work work) {
        return this.workRepository.save(work);
    }

    public Work save(WorkDto workDto) {
        Work work = new Work();

        work.setTitle(workDto.getTitle());
        work.setDescription(workDto.getDescription());
        work.setImageUrl(workDto.getImageUrl());
        work.setDateOfRealization(workDto.getDateOfRealization());

        if (workDto.getArtistId() != null) {
            Artist artist = this.artistService.getById(workDto.getArtistId());
            work.setArtist(artist);
        }

        if (workDto.getCollectionId() != null) {
            Collection collection = this.collectionService.getById(workDto.getCollectionId());
            work.setCollection(collection);
        }

        return this.workRepository.save(work);
    }
}