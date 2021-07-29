package com.codingdojo.magictouch.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codingdojo.magictouch.models.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	List<Recipe>findAll();
	
	@Query(value = "select * from Recipe e wherre e.title like %:keyword%", nativeQuery=true)
	List<Recipe> findByKeyword(@Param("keyword") String keyword);
}
