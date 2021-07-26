package com.codingdojo.magictouch.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.magictouch.models.FollowingRecipe;


@Repository
public interface FollowingRecipeRepository extends CrudRepository<FollowingRecipe, Long> {
	List<FollowingRecipe>findALL();

}