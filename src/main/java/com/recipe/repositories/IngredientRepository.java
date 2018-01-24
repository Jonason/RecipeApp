package com.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.recipe.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
