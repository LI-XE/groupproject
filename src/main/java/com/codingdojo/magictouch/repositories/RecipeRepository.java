package com.codingdojo.magictouch.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.magictouch.models.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	List<Recipe>findALL();

}
