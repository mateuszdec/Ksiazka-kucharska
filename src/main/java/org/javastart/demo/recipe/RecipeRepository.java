package org.javastart.demo.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT m FROM Recipe m " +
            " WHERE 1=1 " +
            " AND (:category IS NULL OR m.category = :category)"
    )
    List<Recipe> recipeList();

//    Optional<Recipe> findByNameIgnoreCase(String name);

//    List<Recipe> findUsingFilters(@Param("name") String name,
//                                 @Param("category") Category category);
}
