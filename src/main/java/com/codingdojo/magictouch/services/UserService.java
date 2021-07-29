package com.codingdojo.magictouch.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.magictouch.models.User;
import com.codingdojo.magictouch.repositories.UserRepository;





@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	
	// Register user and hash their password
		public User registration(User user){
			String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(hashed);
			return this.uRepo.save(user);
		}
		
		// find user by email
		public User findByEmail(String email) {
			return this.uRepo.findByEmail(email);
		}
		
		// find user by id
		public User findById(Long id) {
			Optional<User> user = this.uRepo.findById(id);
			if(user.isPresent()) {
				return user.get();
			} else {
				return null;
			}
		}
		
		//authenticate user
		public boolean authenticateUser1(String email, String password) {
	        // first find the user by email
	        User user = this.uRepo.findByEmail(email);
	        // if we can't find it by email, return false
	        if(user == null) {
	            return false;
	        } else {
	            // if the passwords match, return true, else, return false
	            if(BCrypt.checkpw(password, user.getPassword())) {
	                return true;
	            } else {
	                return false;
	            }
	        }
		}
	
	public User FindOneUser(Long id) {
		User User = this.uRepo.findById(id).orElse(null);
		return User;
	}
	
	public User FindByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
	
	
	public List<User> allUsers(){
		return this.uRepo.findAll();
	}
	
	public User RegisterUser(User user) {
		String hash =BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hash);
		return this.uRepo.save(user);
	}
	
	public boolean authenticateUser(String email, String password) {
		User user= this.uRepo.findByEmail(email);
		
		if(user==null) {
			return false;
		}
		
		return BCrypt.checkpw(password, user.getPassword());
	}

}
