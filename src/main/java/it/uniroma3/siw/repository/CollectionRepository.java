package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, Long> {
    List<Collection> findAll();
}