package org.javastart.demo.category;

import org.javastart.demo.Recipe;
import org.javastart.demo.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    public CategoryController(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/category/{id}")
    public String showCategory(@PathVariable() Long id, Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("category", categoryList);
        List<Recipe> recipeList = recipeRepository.recipeList();
        model.addAttribute("recipeList", recipeList);


        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            model.addAttribute("categories", category);
        }
        return "category";
    }
}
