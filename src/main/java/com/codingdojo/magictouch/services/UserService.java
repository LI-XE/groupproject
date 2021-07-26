package com.codingdojo.magictouch.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.magictouch.models.User;
import com.codingdojo.magictouch.repositories.UserRepository;





@Service
public class UserService {
	@Autowired
	private UserRepository URepo;
	
	public User FindOneUser(Long id) {
		User User = this.URepo.findById(id).orElse(null);
		return User;
	}
	
	public User FindByEmail(String email) {
		return this.URepo.findByEmail(email);
	}
	
	
	public List<User> allUsers(){
		return this.URepo.findALL();
	}
	
	public User RegisterUser(User user) {
		String hash =BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hash);
		return this.URepo.save(user);
	}
	
	public boolean authenticateUser(String email, String password) {
		User user= this.URepo.findByEmail(email);
		
		if(user==null) {
			return false;
		}
		
		return BCrypt.checkpw(password, user.getPassword());
	}

}
