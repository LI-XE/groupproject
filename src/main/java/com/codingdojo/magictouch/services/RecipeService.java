package com.codingdojo.magictouch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.magictouch.models.Recipe;
import com.codingdojo.magictouch.repositories.RecipeRepository;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository RRepo;
	
	public Recipe FindOneRecipe(Long id) {
		Recipe Recipe = this.RRepo.findById(id).orElse(null);
		return Recipe;
	}
	
	public List<Recipe> allRecipe(){
		return this.RRepo.findALL();
	}
	
	public Recipe newRecipe(Recipe Recipe) {
		return this.RRepo.save(Recipe);
	}
	
	public Recipe editRecipe(Recipe Recipe) {
		return this.RRepo.save(Recipe);
	}
	
	public void delete(Long id) {
		 this.RRepo.deleteById(id);
	}

}
