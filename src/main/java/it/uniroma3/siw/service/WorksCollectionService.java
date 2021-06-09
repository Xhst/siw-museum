package it.uniroma3.siw.service;

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
}
