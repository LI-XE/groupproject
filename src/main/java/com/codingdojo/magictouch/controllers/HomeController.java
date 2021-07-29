package com.codingdojo.magictouch.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.magictouch.models.Category;
import com.codingdojo.magictouch.models.Comment;
import com.codingdojo.magictouch.models.Ingredient;
import com.codingdojo.magictouch.models.Recipe;
import com.codingdojo.magictouch.models.Step;
import com.codingdojo.magictouch.models.User;
import com.codingdojo.magictouch.services.CategoryService;
import com.codingdojo.magictouch.services.CommentService;
import com.codingdojo.magictouch.services.IngredientService;
import com.codingdojo.magictouch.services.RecipeService;
import com.codingdojo.magictouch.services.StepService;
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
	private StepService sService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private IngredientService iService;
	@Autowired
	private UserValidator validator;
	
	
	
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
			return "redirect:/home";
		}
		
		@PostMapping("/login")
		public String loginUser(@RequestParam("loginEmail") String email, @RequestParam("loginPassword") String password, RedirectAttributes redirectAttrs, Model model, HttpSession session) {
			if(!this.uService.authenticateUser(email, password)) {
				redirectAttrs.addFlashAttribute("loginError", "Invalid Credentials");
				return "redirect:/";
			}
			User user = this.uService.FindByEmail(email);
			session.setAttribute("user__id", user.getId());
			return "redirect:/home";
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}
		
	// index
	@GetMapping("/home")
	public String home(HttpSession session, Model model, String keyword) {
		
		Long userId = (Long) session.getAttribute("user__id");
		User user = this.uService.findById(userId);
		List<Recipe> recipes = this.rService.allRecipes();
		List<Category> categories = this.cateService.allCategories();
		List<Comment> comments = this.commentService.allComments();
		model.addAttribute("recipes", recipes);
		model.addAttribute("categories", categories);
		model.addAttribute("user", user);
		model.addAttribute("comments", comments);
		
		if(keyword != null) {
			model.addAttribute("recipes", rService.findByKeyword(keyword));
		}
		else
		{
			model.addAttribute("recipes", rService.allRecipes());
		}
		return "home.jsp";
	}
	
	// new recipe
	@GetMapping("/recipes/new")
	public String addRecipe(@ModelAttribute("recipe") Recipe recipe, @ModelAttribute("ingredients") Ingredient ingredient,  @ModelAttribute("steps") Step step, HttpSession session, Model model) {
//		if(session.getAttribute("user_id") == null) {
//			return "redirect:/";
//		}
		Long userId = (Long) session.getAttribute("user__id");
		User user = this.uService.FindOneUser(userId);
		model.addAttribute("recipe", recipe);
		model.addAttribute("ingredients", ingredient);
		model.addAttribute("steps", step);
		model.addAttribute("user", user);
		return "RecipeCreator.jsp";
	}
	
	@PostMapping("/recipes/new")
	public String createRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, @ModelAttribute("ingredients") Ingredient ingredient,  @ModelAttribute("steps") Step step, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("user__id");
		User user = this.uService.FindOneUser(userId);
		if(result.hasErrors()) {
			return "RecipeCreator.jsp";
		}
		recipe.setAuthor(user);
		this.rService.newRecipe(recipe);
		return "redirect:/myrecipes";
	}
	
	// get one recipe
	@GetMapping("/recipes/{id}")
	public String showRecipe(@PathVariable("id") Long id, @ModelAttribute("comment") Comment comment, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user__id");
		User user = this.uService.FindOneUser(userId);
		Recipe this_recipe = this.rService.FindOneRecipe(id);
		List<Comment> comments = this.commentService.allComments();
		model.addAttribute("recipe", this_recipe);
		model.addAttribute("user", user);
		model.addAttribute("comments", comments);
		
		Date date = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("dd MMMM YYYY h:mma");
		model.addAttribute("currentTime", dt.format(date));
		return "showRecipe.jsp";
	}
	
	// update recipes
		@GetMapping("/recipes/edit/{id}")
		public String editRecipes(@ModelAttribute("updaterecipe") Recipe recipe, HttpSession session, Model model, @PathVariable("id") Long id) {
			Long userId = (Long) session.getAttribute("user__id");
			User user = this.uService.FindOneUser(userId);
			model.addAttribute("recipe", this.rService.FindOneRecipe(id));
			return "editrecipe.jsp";
		}
		
		@PutMapping("/recipes/edit/{id}")
		public String addrecipes(@Valid @ModelAttribute("updaterecipe") Recipe recipe, BindingResult result, @PathVariable("id") Long id, Model model, HttpSession session) {
			Long userId = (Long) session.getAttribute("user__id");
			User user = this.uService.FindOneUser(userId);
			if(result.hasErrors()) {
				model.addAttribute("recipe", this.rService.FindOneRecipe(id));
				return "editrecipe.jsp";
			}
			model.addAttribute("recipe", this.rService.FindOneRecipe(id));
			model.addAttribute("user", user);
			this.rService.editRecipe(recipe);
			return "redirect:/myrecipes";
		}
	
	// delete recipe
	@GetMapping("/recipes/delete/{id}")
	public String deleteRecipe(@PathVariable("id") Long id) {
		this.rService.delete(id);;
		return "redirect:/myrecipes";
	}
	
	// my recipes
	@GetMapping("/myrecipes")
	public String myrecipes(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user__id");
		User user = this.uService.FindOneUser(userId);
		List<Recipe> recipes = this.rService.allRecipes();
		model.addAttribute("user", user);
		model.addAttribute("recipes", recipes);
		return "MyRecipe.jsp";
	}
	
	
	// continue add ingredients
		@GetMapping("/recipes/{id}/addingredients")
		public String addIngredients(@PathVariable("id") Long id, @ModelAttribute("recipe") Recipe recipe, @ModelAttribute("ingredients") Ingredient ingredient,  @ModelAttribute("steps") Step step, HttpSession session, Model model) {
			Long userId = (Long) session.getAttribute("user__id");
			User user = this.uService.FindOneUser(userId);
			Recipe this_recipe = this.rService.FindOneRecipe(id);
			model.addAttribute("recipe", this_recipe);
			model.addAttribute("ingredients", ingredient);
			model.addAttribute("user", user);
			return "addingredients.jsp";
		}
		
		@PostMapping("/recipes/{id}/addingredients")
		public String createIngredients(@PathVariable("id") Long id, @Valid @ModelAttribute("recipe") Recipe recipe, @ModelAttribute("ingredients") Ingredient ingredient,  @ModelAttribute("steps") Step step, BindingResult result, HttpSession session, Model model) {
			Long userId = (Long) session.getAttribute("user__id");
			User user = this.uService.FindOneUser(userId);
			Recipe this_recipe = this.rService.FindOneRecipe(id);
			model.addAttribute("recipe", this_recipe);
			model.addAttribute("ingredients", ingredient);
			model.addAttribute("user", user);
			if(result.hasErrors()) {
				return "addingredients.jsp";
			}
			ingredient.setRecipe(recipe);
			this.iService.newIngredient(ingredient);
			return "redirect:/recipes/{id}/addingredients";
		}
	
		// continue add steps
		@GetMapping("/recipes/{id}/addsteps")
		public String addSteps(@PathVariable("id") Long id, @ModelAttribute("recipe") Recipe recipe, @ModelAttribute("ingredients") Ingredient ingredient,  @ModelAttribute("steps") Step step, HttpSession session, Model model) {
			Long userId = (Long) session.getAttribute("user__id");
			User user = this.uService.FindOneUser(userId);
			Recipe this_recipe = this.rService.FindOneRecipe(id);
			model.addAttribute("recipe", this_recipe);
			model.addAttribute("steps", step);
			model.addAttribute("user", user);
			return "addsteps.jsp";
		}
		
		@PostMapping("/recipes/{id}/addsteps")
		public String createSteps(@PathVariable("id") Long id, @Valid @ModelAttribute("recipe") Recipe recipe, @ModelAttribute("steps") Step step, BindingResult result, HttpSession session, Model model) {
			Long userId = (Long) session.getAttribute("user__id");
			User user = this.uService.FindOneUser(userId);
			Recipe this_recipe = this.rService.FindOneRecipe(id);
			model.addAttribute("recipe", this_recipe);
			model.addAttribute("steps", step);
			model.addAttribute("user", user);
			if(result.hasErrors()) {
				return "addsteps.jsp";
			}
			step.setRecipe(recipe);
			this.sService.newStep(step);
			return "redirect:/recipes/{id}/addsteps";
		}
	
	
	@PostMapping("/recipes/{id}/comment")
	public String createComment(@PathVariable("id") Long id, @Valid @ModelAttribute("comment") Comment comment, BindingResult result, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user__id");
		User user = this.uService.FindOneUser(userId);
		Recipe this_recipe = this.rService.FindOneRecipe(id);
		model.addAttribute("recipe", this_recipe);
		model.addAttribute("comment", comment);
		model.addAttribute("user", user);
		if(result.hasErrors()) {
			return "showRecipe.jsp";
		}
		comment.setRecipe(this_recipe);
		comment.setCommentPoster(user);
		this.commentService.newComment(comment);
		return "/recipes/{id}";
	}
	
	@GetMapping("/like/{id}")
	public String like(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long)session.getAttribute("user__id");
		Long recipeId = id;
		User liker = this.uService.findById(userId);
		Recipe likedRecipe = this.rService.FindOneRecipe(recipeId);
		this.rService.addLiker(liker, likedRecipe);
		return "redirect:/home";
	}
	
	@GetMapping("/unlike/{id}")
	public String unlike(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long)session.getAttribute("user__id");
		Long recipeId = id;
		User liker = this.uService.findById(userId);
		Recipe likedRecipe = this.rService.FindOneRecipe(recipeId);
		this.rService.removeLiker(liker, likedRecipe);
		return "redirect:/home";
	}
	
	
}
