package org.javastart.demo;

import org.dom4j.rule.Mode;
import org.javastart.demo.category.Category;
import org.javastart.demo.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {

//    @Autowired
//    private EntityManager entityManager;
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }




//    @GetMapping("/")
//    public String home(Model model) {
//
////        TypedQuery<Recipe> query = entityManager.createQuery("SELECT r FROM Recipe r", Recipe.class);
////        List<Recipe> recipes = query.getResultList();
////        model.addAttribute("recipes", recipes);
//        List<Recipe> recipes = recipeRepository.findAll();
//        model.addAttribute("recipes", recipes);
//
//        List<Category> categories = recipeRepository.findAll();
//        model.addAttribute("category", categories);
////        model.addAttribute("addRecipe", new Recipe());
//
//        return "home";
//    }

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
        }
        return "recipe";
    }



//    @GetMapping("/przepis/{name}")
//    private String recipeDetails(@RequestParam String name, Model model) {
//        Optional<Recipe> optional = recipeRepository.findByNameIgnoreCase(name);
//                if (optional.isPresent()) {
//            Recipe recipe = optional.get();
//            model.addAttribute("recipe", recipe);
//            return "recipe";
//        } else {
//            return "redirect:/";
//        }
//    }


//    @GetMapping("/przepis")
//    public String recipeInfo(@RequestParam String name, Model model) {
//        Optional<Recipe> optional = recipeRepository.findByNameIgnoreCase(name);
//        optional.ifPresent(recipe -> model.addAttribute("recipe", recipe));
//        return optional.map(recipe -> "recipe").orElse("redirect:/");
//    }


//    @GetMapping("/recipe/{name}")
//    public String recipeDetails(@RequestParam String name, Model model) {
//        Optional<Recipe> optional = recipeRepository.findByNameIgnoreCase(name);
//        optional.ifPresent(recipe -> model.addAttribute("recipe", recipe));
//        return optional.map(recipe -> "recipe").orElse("redirect:/");
////        return "recipe";
//    }

//    @GetMapping("/recipe/{name}")
//    public String recipeDetails(@PathVariable String name, Model model) {
//        Optional<Recipe> optional = recipeRepository.findByNameIgnoreCase(name);
//        if (optional.isPresent()) {
//            Recipe recipe = optional.get();
//            model.addAttribute("recipe", recipe);
//            return "recipe";
//        } else {
//            return "redirect:/";
//        }
//    }

//
////        Optional<Recipe> item = recipeRepository.findByNameIgnoreCase(name.replaceAll("-", " "));
////        item.ifPresent(recipe -> model.addAttribute("recipe", recipe));
////        return item.map(recipe -> "recipe").orElse("redirect:/");
//    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        recipeRepository.deleteById(id);
        return "redirect:/";
    }
//
    @PostMapping("/editrecipe")
    public String editRecipe(Recipe recipe){
        recipeRepository.save(recipe);
        return "redirect:/recipe/" + recipe.getId();
    }
}
