package com.codingdojo.magictouch.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.magictouch.models.Category;
import com.codingdojo.magictouch.models.Comment;
import com.codingdojo.magictouch.models.Recipe;
import com.codingdojo.magictouch.models.User;
import com.codingdojo.magictouch.services.CategoryService;
import com.codingdojo.magictouch.services.CommentService;
import com.codingdojo.magictouch.services.RecipeService;
import com.codingdojo.magictouch.services.UserService;
import com.codingdojo.magictouch.validators.UserValidator;

@Controller
public class HomeController {

	@Autowired
	private UserService uService;
	@Autowired
	private CategoryService cateService;
	@Autowired
	private RecipeService rService;
	@Autowired
	private UserValidator validator;
	@Autowired
	private CommentService cService;
	
	// register, login, logout
		@GetMapping("/")
		public String index(@ModelAttribute("user") User user) {
			return "index.jsp";
		}
		
		@PostMapping("/registration")
		public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
			this.validator.validate(user, result);
			if(result.hasErrors()) {
				return "index.jsp";
			}
			User newUser = this.uService.registration(user);
			session.setAttribute("user__id", newUser.getId());
			return "redirect:/recipes";
		}
		
		@PostMapping("/login")
		public String loginUser(@RequestParam("loginEmail") String email, @RequestParam("loginPassword") String password, RedirectAttributes redirectAttrs, Model model, HttpSession session) {
			if(!this.uService.authenticateUser(email, password)) {
				redirectAttrs.addFlashAttribute("loginError", "Invalid Credentials");
				return "redirect:/";
			}
			User user = this.uService.FindByEmail(email);
			session.setAttribute("user__id", user.getId());
			return "redirect:/recipes";
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}
		
	// index
	@GetMapping("/home")
	public String index(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user__id");
		User user = this.uService.findById(userId);
		List<Recipe> recipes = this.rService.allRecipes();
		List<Category> categories = this.cateService.allCategories();
		model.addAttribute("recipes", recipes);
		model.addAttribute("categories", categories);
		model.addAttribute("user", user);
		return "home.jsp";
	}
	
	// get one recipe
	@GetMapping("/recipes")
	public String showRecipe( Model model, HttpSession session) {


		return "RecipeCreator.jsp";
	}
	
	
//	@GetMapping("/recipes/id")
//	public String showRecipe(@PathVariable("id") Long id, Model model, HttpSession session) {
//		Long userId = (Long) session.getAttribute("user__id");
//		User user = this.uService.FindOneUser(userId);
//		model.addAttribute("recipe", this.rService.FindOneRecipe(id));
//		model.addAttribute("user", user);
//		return "RecipeCreator.jsp";
//	}
//	
//	// new recipe
//	@GetMapping("/recipes/new")
//	public String addRecipe(@ModelAttribute("newRecipe") Recipe recipe, HttpSession session, Model model) {
//		Long userId = (Long) session.getAttribute("user__id");
//		User user = this.uService.FindOneUser(userId);
//		model.addAttribute("recipe", recipe);
//		return "RecipeCreator.jsp";
//	}
//	
//	@PostMapping("/recipes/new")
//	public String createRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult result, HttpSession session) {
//		Long userId = (Long) session.getAttribute("user__id");
//		User user = this.uService.FindOneUser(userId);
//		if(result.hasErrors()) {
//			return "RecipeCreator.jsp";
//		}
//		recipe.setAuthor(user);
//		this.rService.newRecipe(recipe);
//		return "redirect:/myrecipes";
//	}
//	
	@GetMapping("/recipes/delete/{id}")
	public String deleteRecipe(@PathVariable("id") Long id) {
		this.rService.delete(id);;
		return "redirect:/myrecipes";
	}

	@GetMapping("/recipes/new")
	public String createRecipe(@ModelAttribute("recipe") Recipe recipe, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user__id");
		User user = this.uService.FindOneUser(userId);
		model.addAttribute("recipe", recipe);
		return "addRecipe.jsp";
	}
	
	@PostMapping("/recipes/new")
	public String addRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("user__id");
		User user = this.uService.FindOneUser(userId);
		if(result.hasErrors()) {
			return "addRecipe.jsp";
		}
		recipe.setAuthor(user);
		this.rService.newRecipe(recipe);
		return "redirect:/myrecipes";
	}
	
	@GetMapping("/like/{id}")
	public String like(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long)session.getAttribute("user__id");
		Long recipeId = id;
		User liker = this.uService.findById(userId);
		Recipe likedRecipe = this.rService.FindOneRecipe(recipeId);
		this.rService.addLiker(liker, likedRecipe);
		return "redirect:/recipes";
	}
	
	@GetMapping("/unlike/{id}")
	public String unlike(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long)session.getAttribute("user__id");
		Long recipeId = id;
		User liker = this.uService.findById(userId);
		Recipe likedRecipe = this.rService.FindOneRecipe(recipeId);
		this.rService.removeLiker(liker, likedRecipe);
		return "redirect:/recipes";
	}
	
	@GetMapping("/recipes/comment/new")
	public String createComment(@PathVariable("id") Long id, @ModelAttribute("comment") Comment comment, HttpSession session, Model model) {
//		Long userId = (Long) session.getAttribute("user__id");
//		User user = this.uService.FindOneUser(userId);
//		Long recipeId = id;
//		Recipe recipe = this.rService.FindOneRecipe(recipeId);
		model.addAttribute("comment", comment);
		return "addRecipe.jsp";
	}
	
	@PostMapping("/recipes/comment/new")
	public String addRecipe(@PathVariable("id") Long id, @Valid @ModelAttribute("comment") Comment comment, BindingResult result, HttpSession session) {
//		Long userId = (Long) session.getAttribute("user__id");
//		User user = this.uService.FindOneUser(userId);
//		Long recipeId = id;
//		Recipe recipe = this.rService.FindOneRecipe(recipeId);
		if(result.hasErrors()) {
			return "addComment.jsp";
		}
//		comment.setCommentPoster(user);
//		comment.setRecipe(recipe);
		this.cService.newComment(comment);
		return "redirect:/recipes/";
	}
}
