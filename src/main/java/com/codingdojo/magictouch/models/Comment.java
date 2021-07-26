package com.codingdojo.magictouch.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, max = 200)
	private String comment;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recipe_id")
	private Recipe recipe;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="followingrecipe_id")
	private Recipe followingrecipe;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "comments",
			joinColumns = @JoinColumn(name = "comment_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> commentPosters;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "commentLikes",
			joinColumns = @JoinColumn(name = "comment_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> commentLikers;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-DD HH:mm:ss")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-DD HH:mm:ss")
	private Date updatedAt;
	
	public Comment() {
		
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Recipe getFollowingrecipe() {
		return followingrecipe;
	}

	public void setFollowingrecipe(Recipe followingrecipe) {
		this.followingrecipe = followingrecipe;
	}

	public List<User> getCommentPosters() {
		return commentPosters;
	}

	public void setCommentPosters(List<User> commentPosters) {
		this.commentPosters = commentPosters;
	}

	public List<User> getCommentLikers() {
		return commentLikers;
	}

	public void setCommentLikers(List<User> commentLikers) {
		this.commentLikers = commentLikers;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
