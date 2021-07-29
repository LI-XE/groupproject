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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="recipes")
public class Recipe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, max = 200)
	private String title;
	
	@Column(nullable = true, length = 64)
    private String photo;
	
	@NotBlank
	private String description;
	
	@OneToMany(mappedBy="recipe", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Ingredient> ingredients;
	
	@OneToMany(mappedBy="recipe", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Step> steps;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User author;
	
	@OneToMany(mappedBy="recipe", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<FollowingRecipe> followingRecipes;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "categories_recipes",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id")
			)
	private List<Category> categories;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "likes",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> likers;
	
	@OneToMany(mappedBy="recipe", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Comment> comments;
	
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

	public Recipe() {
		
	}
	
	public Recipe(Long id, @Size(min = 3, max = 200) String title, String photo, @NotBlank String description,
			List<Ingredient> ingredients, List<Step> steps, User author, List<FollowingRecipe> followingRecipes,
			List<Category> categories, List<User> likers, List<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.photo = photo;
		this.description = description;
		this.ingredients = ingredients;
		this.steps = steps;
		this.author = author;
		this.followingRecipes = followingRecipes;
		this.categories = categories;
		this.likers = likers;
		this.comments = comments;
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

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<FollowingRecipe> getFollowingRecipes() {
		return followingRecipes;
	}

	public void setFollowingRecipes(List<FollowingRecipe> followingRecipes) {
		this.followingRecipes = followingRecipes;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<User> getLikers() {
		return likers;
	}

	public void setLikers(List<User> likers) {
		this.likers = likers;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
