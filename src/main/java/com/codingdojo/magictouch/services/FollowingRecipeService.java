package com.codingdojo.magictouch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.magictouch.models.FollowingRecipe;
import com.codingdojo.magictouch.repositories.FollowingRecipeRepository;

@Service
public class FollowingRecipeService {
	@Autowired
	private FollowingRecipeRepository fRepo;
	
	public FollowingRecipe FindOneRecipe(Long id) {
		FollowingRecipe followingRecipe = this.fRepo.findById(id).orElse(null);
		return followingRecipe;
	}
	
	public List<FollowingRecipe> allRecipes(){
		return this.fRepo.findALL();
	}
	
	public FollowingRecipe newFollowingRecipe(FollowingRecipe followingRecipe) {
		return this.fRepo.save(followingRecipe);
	}
	
	public FollowingRecipe editFollowingRecipe(FollowingRecipe followingRecipe) {
		return this.fRepo.save(followingRecipe);
	}
	
	public void delete(Long id) {
		 this.fRepo.deleteById(id);
	}

}
