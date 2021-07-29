package com.codingdojo.magictouch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.magictouch.models.Ingredient;
import com.codingdojo.magictouch.repositories.IngredientRepository;

@Service
public class IngredientService {
	@Autowired
	private IngredientRepository iRepo;
	
	public Ingredient FindOneIngredient(Long id) {
		Ingredient ingredient = this.iRepo.findById(id).orElse(null);
		return ingredient;
	}
	
	public List<Ingredient> allIngredients(){
		return this.iRepo.findAll();
	}
	
	public Ingredient newIngredient(Ingredient ingredient) {
		return this.iRepo.save(ingredient);
	}
	
	public Ingredient editIngredient(Ingredient ingredient) {
		return this.iRepo.save(ingredient);
	}
	
	public void delete(Long id) {
		 this.iRepo.deleteById(id);
	}

}
