package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Work;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends CrudRepository<Work, Long> {
    List<Work> findAll();
}
