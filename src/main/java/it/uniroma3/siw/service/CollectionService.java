package it.uniroma3.siw.service;

import it.uniroma3.siw.dto.CollectionDto;
import it.uniroma3.siw.model.Collection;
import it.uniroma3.siw.repository.CollectionRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    private final UserService userService;


    public boolean existsById(Long id) {
        return this.getById(id) != null;
    }

    public Collection getById(Long id) {
        return this.collectionRepository.findById(id)
                .orElse(null);
    }

    public void deleteById(Long id) {
        this.collectionRepository.deleteById(id);
    }

    public List<Collection> getAll() {
        return this.collectionRepository.findAll();
    }

    public Collection save(CollectionDto collectionDto) {
        Collection collection = new Collection();

        collection.setName(collectionDto.getName());
        collection.setDescription(collectionDto.getDescription());
        collection.setCurator(this.userService.getById(collectionDto.getCuratorId()));

        return this.collectionRepository.save(collection);
    }
}
