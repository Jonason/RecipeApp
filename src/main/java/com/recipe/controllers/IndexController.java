package com.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.domain.Category;
import com.recipe.domain.Recipe;
import com.recipe.domain.UnitOfMeasure;
import com.recipe.repositories.CategoryRepository;
import com.recipe.repositories.RecipeRepository;
import com.recipe.repositories.UnitOfMeasureRepository;
import com.recipe.services.RecipeService;

@Controller
public class IndexController {

//	private CategoryRepository categoryRepository;
//	private UnitOfMeasureRepository unitOfMeasureRepository;
//	private RecipeRepository recipeRepository;
//
//	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
//		this.categoryRepository = categoryRepository;
//		this.unitOfMeasureRepository = unitOfMeasureRepository;
//	}
//
//	@RequestMapping({ "", "/", "/index" })
//	public String getIndexPage(Model model) {
//
//		Optional<Category> categoryOptional = categoryRepository.findByName("American");
//		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByOum("Teaspoon");
//		
//
//		System.out.println("Cat Id is: " + categoryOptional.get().getId());
//		System.out.println("UOM Id is: " + unitOfMeasureOptional.get().getId());
//		
//		model.addAttribute("recipes", recipeRepository.findAll());
//		return "index";
//	}
	
	private final RecipeService recipeService;
	
	public IndexController(RecipeService recipeService)
	{
		this.recipeService = recipeService;
	}
	
	@RequestMapping({"", "/", "index"})
	public String getIndexPage(Model model)
	{
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
}
