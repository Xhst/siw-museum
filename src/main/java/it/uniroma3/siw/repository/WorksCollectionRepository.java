package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.WorksCollection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorksCollectionRepository extends CrudRepository<WorksCollection, Long> {
    List<WorksCollection> findAll();
}