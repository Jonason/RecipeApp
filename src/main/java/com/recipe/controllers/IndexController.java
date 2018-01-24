package com.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.domain.Category;
import com.recipe.domain.UnitOfMeasure;
import com.recipe.repositories.CategoryRepository;
import com.recipe.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {

	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage() {

		Optional<Category> categoryOptional = categoryRepository.findByName("American");
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByOum("Teaspoon");

		System.out.println("Cat Id is: " + categoryOptional.get().getId());
		System.out.println("UOM Id is: " + unitOfMeasureOptional.get().getId());

		return "index";
	}
}
