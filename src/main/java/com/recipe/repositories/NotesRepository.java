package com.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.recipe.domain.Notes;

public interface NotesRepository extends CrudRepository<Notes, Long> {

}
