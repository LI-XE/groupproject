package com.codingdojo.magictouch.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String username;
	
	@Email
	@NotBlank
	private String email;
	@Size(min=8, message="Password must be greater than 8 characters")
	private String password;
	@Transient
	private String passwordConfirmation;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Recipe> recipes;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "likes",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "recipe_id")
			)
	private List<Recipe> likedRecipes;
	
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "commentLikes",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "comment_id")
			)
	private List<Comment> likedComments;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<FollowingRecipe> followingRecipes;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "followingrecipeLikes",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "followingrecipe_id")
			)
	private List<FollowingRecipe> likedFollowingrecipes;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-DD HH:mm:ss")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-DD HH:mm:ss")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	public User() {
	
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public List<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	public List<Recipe> getLikedRecipes() {
		return likedRecipes;
	}
	public void setLikedRecipes(List<Recipe> likedRecipes) {
		this.likedRecipes = likedRecipes;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Comment> getLikedComments() {
		return likedComments;
	}
	public void setLikedComments(List<Comment> likedComments) {
		this.likedComments = likedComments;
	}
	public List<FollowingRecipe> getFollowingRecipes() {
		return followingRecipes;
	}
	public void setFollowingRecipes(List<FollowingRecipe> followingRecipes) {
		this.followingRecipes = followingRecipes;
	}
	public List<FollowingRecipe> getLikedFollowingrecipes() {
		return likedFollowingrecipes;
	}
	public void setLikedFollowingrecipes(List<FollowingRecipe> likedFollowingrecipes) {
		this.likedFollowingrecipes = likedFollowingrecipes;
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