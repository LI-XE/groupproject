package com.codingdojo.magictouch.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.magictouch.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	List<User>findALL();
	boolean existsByEmail(String email);
	User findByEmail(String email);
}