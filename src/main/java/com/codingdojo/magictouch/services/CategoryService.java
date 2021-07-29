package com.codingdojo.magictouch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.magictouch.models.Category;
import com.codingdojo.magictouch.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository cateRepo;
	
	public List<Category> allCategories(){
		return this.cateRepo.findAll();
	}
	
	public Category singleCategory(Long id) {
		Category category = this.cateRepo.findById(id).orElse(null);
		return category;
	}
	
	public Category newCategory(Category category) {
		return this.cateRepo.save(category);
	}
	
	public Category editCategory(Category category) {
		return this.cateRepo.save(category);
	}
	
	public void delete(Long id) {
		 this.cateRepo.deleteById(id);
	}
}
