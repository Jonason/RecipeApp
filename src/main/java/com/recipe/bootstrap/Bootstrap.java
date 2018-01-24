package com.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.recipe.domain.Category;
import com.recipe.domain.Difficulty;
import com.recipe.domain.Ingredient;
import com.recipe.domain.Notes;
import com.recipe.domain.Recipe;
import com.recipe.repositories.CategoryRepository;
import com.recipe.repositories.IngredientRepository;
import com.recipe.repositories.RecipeRepository;
import com.recipe.repositories.UnitOfMeasureRepository;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private CategoryRepository categoryRepository;
	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	private IngredientRepository ingredientRepository;

	public Bootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}

	private void initData() {

		Category categoryMex = new Category();
		categoryMex.setName("Mexican");
		categoryRepository.save(categoryMex);

		Recipe guacamole = new Recipe();

		Ingredient ing1 = new Ingredient("ripe avocados", BigDecimal.valueOf(2), null, guacamole);
		Ingredient ing2 = new Ingredient("Kosher salt", BigDecimal.valueOf(.5),
				unitOfMeasureRepository.findByOum("Teaspoon").get(), guacamole);

		// 1 Tbsp of fresh lime juice or lemon juice
		Ingredient ing3 = new Ingredient("fresh lime juice or lemon juice", BigDecimal.valueOf(1),
				unitOfMeasureRepository.findByOum("Tablespoon").get(), guacamole);

		// 2 Tbsp to 1/4 cup of minced red onion or thinly sliced green onion
		Ingredient ing4 = new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(2),
				unitOfMeasureRepository.findByOum("Cup").get(), guacamole);

		// 1-2 serrano chiles, stems and seeds removed, minced
		Ingredient ing5 = new Ingredient("serrano chiles, stems and seeds removed, minced", BigDecimal.valueOf(2), null,
				guacamole);

		// 2 tablespoons cilantro (leaves and tender stems), finely chopped
		Ingredient ing6 = new Ingredient("cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2),
				unitOfMeasureRepository.findByOum("Tablespoon").get(), guacamole);

		/// A dash of freshly grated black pepper
		Ingredient ing7 = new Ingredient("dash of freshly grated black pepper", BigDecimal.valueOf(1),
				unitOfMeasureRepository.findByOum("Dash").get(), guacamole);

		// 1/2 ripe tomato, seeds and pulp removed, chopped
		Ingredient ing8 = new Ingredient("2 ripe tomato, seeds and pulp removed, chopped", BigDecimal.valueOf(.5), null,
				guacamole);

		Set<Ingredient> ingredients = new HashSet<>();
		ingredients.add(ing1);
		ingredients.add(ing2);
		ingredients.add(ing3);
		ingredients.add(ing4);
		ingredients.add(ing5);
		ingredients.add(ing6);
		ingredients.add(ing7);
		ingredients.add(ing8);

		guacamole.setIngredients(ingredients);
		// guacamole.setCookTime();
		guacamole.setDescription("How to Make Perfect Guacamole Recipe");
		guacamole.setDifficulty(Difficulty.EASY);

		String directions = "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.";
		directions += "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)";
		directions += "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.";
		directions += "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness. ";
		directions += "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste. ";
		directions += "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.";
		directions += "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving. ";
		directions += "Variations ";
		directions += "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados. ";
		directions += "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole). ";
		directions += "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole. ";
		directions += "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great. ";

		Notes notes = new Notes();
		notes.setRecipeNotes(directions);

		guacamole.setNotes(notes);
		// guacamole.setDirections(directions);
		guacamole.setServings(4);
		// guacamole.setSource();
		guacamole.setPrepTime(10);
		guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

		recipeRepository.save(guacamole);
	}
}
