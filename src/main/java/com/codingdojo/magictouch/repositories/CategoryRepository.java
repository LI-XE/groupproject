package com.codingdojo.magictouch.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.magictouch.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	List<Category>findALL();

}
