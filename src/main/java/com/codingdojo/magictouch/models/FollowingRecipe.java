package com.codingdojo.magictouch.models;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="followingRecipes")
public class FollowingRecipe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, max = 200)
	private String title;
	
	@Column(nullable = true, length = 64)
    private String photo;
	
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="followingRecipe_id")
	private User followingRecipePoster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recipe_id")
	private Recipe recipe;

	@OneToMany(mappedBy="followingrecipe", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User followingPoster;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "followingRecipe_commentLikes",
			joinColumns = @JoinColumn(name = "followingRecipe_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> followingRecipeCommentLikers;
	
	public FollowingRecipe() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	public User getFollowingRecipePoster() {
		return followingRecipePoster;
	}

	public void setFollowingRecipePoster(User followingRecipePoster) {
		this.followingRecipePoster = followingRecipePoster;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getFollowingPoster() {
		return followingPoster;
	}

	public void setFollowingPoster(User followingPoster) {
		this.followingPoster = followingPoster;
	}

	public List<User> getFollowingRecipeCommentLikers() {
		return followingRecipeCommentLikers;
	}

	public void setFollowingRecipeCommentLikers(List<User> followingRecipeCommentLikers) {
		this.followingRecipeCommentLikers = followingRecipeCommentLikers;
	}

	
}
