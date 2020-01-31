package org.javastart.demo.recipe;

import org.javastart.demo.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RecipeController {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/add")
    public String addRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("mode", "add");
        return "addRecipeForm";
    }

    @PostMapping("/add")
    public String addRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return "redirect:/";
    }

    @GetMapping("recipe/{id}")
    public String recipeView(@PathVariable Long id, Model model) {
        Optional<Recipe> optional = recipeRepository.findById(id);
        if (optional.isPresent()) {
            Recipe recipe = optional.get();
            model.addAttribute("recipe", recipe);
            return "recipe";
        } else {
            return "redurect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        recipeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/recipe/edytuj/{id}")
    public String edytujForm(@PathVariable Long id, Model model) {
        Optional<Recipe> optional = recipeRepository.findById(id);

        if (optional.isPresent()) {
            Recipe recipe = optional.get();
            model.addAttribute("recipe", recipe);
            model.addAttribute("mode", "edit");
            return "addRecipeForm";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/editrecipe")
    public String editRecipe(Recipe recipe){
        recipeRepository.save(recipe);
        return "redirect:/recipe/" + recipe.getId();
    }
}
