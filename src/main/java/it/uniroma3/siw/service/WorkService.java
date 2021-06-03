package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Work;
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

    public Work getWorkById(Long id) {
        return this.workRepository.findById(id)
                .orElse(null);
    }

    public List<Work> getAll() {
        return this.workRepository.findAll();
    }

    public Work save(Work work) {
        return this.workRepository.save(work);
    }
}