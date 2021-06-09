package it.uniroma3.siw.service;

import it.uniroma3.siw.dto.CollectionDto;
import it.uniroma3.siw.model.WorksCollection;
import it.uniroma3.siw.repository.WorksCollectionRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class WorksCollectionService {

    private final WorksCollectionRepository worksCollectionRepository;

    private final UserService userService;

    public boolean existsById(Long id) {
        return this.getById(id) != null;
    }

    public WorksCollection getById(Long id) {
        return this.worksCollectionRepository.findById(id)
                .orElse(null);
    }

    public List<WorksCollection> getAll() {
        return this.worksCollectionRepository.findAll();
    }

    public WorksCollection save(CollectionDto collectionDto) {
        WorksCollection collection = new WorksCollection();

        collection.setName(collectionDto.getName());
        collection.setDescription(collectionDto.getDescription());
        collection.setCurator(this.userService.getById(collectionDto.getCuratorId()));

        return this.worksCollectionRepository.save(collection);
    }
}
