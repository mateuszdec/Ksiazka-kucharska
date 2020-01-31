package org.javastart.demo.controller;

import org.javastart.demo.category.Category;
import org.javastart.demo.category.CategoryRepository;
import org.javastart.demo.recipe.Recipe;
import org.javastart.demo.recipe.RecipeRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;

    public Controller(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Recipe> recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("category", categories);
        return "home";
    }
}
