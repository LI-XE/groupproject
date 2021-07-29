package com.codingdojo.magictouch.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.magictouch.models.Recipe;
import com.codingdojo.magictouch.services.RecipeService;

@RestController
	@RequestMapping("/api")
	public class RecipeAPIController {
		private RecipeService rService;
		
		public RecipeAPIController(RecipeService service) {
			this.rService = service;
		}
		
		// Routes	
		@RequestMapping("")
		public List<Recipe> index(){
			return this.rService.allRecipes();
		}
//		
//		@RequestMapping("{id}")
//		public Dog getDog(@PathVariable("id") Long id) {
//			return this.dService.getOneDog(id);
//		}
//		
		@RequestMapping(value="/recipes/new", method=RequestMethod.POST)
		public Recipe create(Recipe recipe) {
			return this.rService.newRecipe(recipe);
		}
//		
//		@RequestMapping(value="/dog/update/{id}", method=RequestMethod.PUT)
//		public Dog edit(@PathVariable("id") Long id, Dog updatedDog) {
//			return this.dService.updateDog(id, updatedDog);
//		}
//		
//		@RequestMapping("/dog/delete/{id}")
//		public String removeDog(@PathVariable("id") Long id) {
//			this.dService.deleteDog(id);
//			return id + " has been removed from the database";
//		}
	}