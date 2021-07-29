package com.codingdojo.magictouch.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.magictouch.models.Ingredient;


@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
	List<Ingredient>findAll();

}