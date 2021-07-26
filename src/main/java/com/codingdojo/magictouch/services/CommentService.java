package com.codingdojo.magictouch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.magictouch.models.Comment;
import com.codingdojo.magictouch.repositories.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository cRepo;
	
	public List<Comment> allComments(){
		return this.cRepo.findALL();
	}
	
	public Comment singleComment(Long id) {
		Comment comment = this.cRepo.findById(id).orElse(null);
		return comment;
	}
	
	public Comment newComment(Comment comment) {
		return this.cRepo.save(comment);
	}
	
	public Comment editComment(Comment comment) {
		return this.cRepo.save(comment);
	}
	
	public void delete(Long id) {
		 this.cRepo.deleteById(id);
	}
}