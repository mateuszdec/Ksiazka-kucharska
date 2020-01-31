package org.javastart.demo;

import org.javastart.demo.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
//    Optional<Recipe> findByNameIgnoreCase(String name);
//    List<Recipe> recipeList();

    @Query("SELECT m FROM Recipe m " +
            " WHERE 1=1 " +
            " AND (:category IS NULL OR m.category = :category)"
    )

    List<Recipe> recipeList();

//    Optional<Recipe> findByNameIgnoreCase(String name);

//    List<Recipe> findUsingFilters(@Param("name") String name,
//                                 @Param("category") Category category);
}
