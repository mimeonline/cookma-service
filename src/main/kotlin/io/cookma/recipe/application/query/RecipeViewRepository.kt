package io.cookma.recipe.application.query

import io.cookma.recipe.application.query.RecipeView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface RecipeViewRepository : JpaRepository<RecipeView, String> {

    fun findByName(@Param("name") name: String): RecipeView
    fun findByRecipeId(@Param("recipeId") recipeId: String): RecipeView
    fun deleteByRecipeId(@Param("recipeId") recipeId: String)
    fun findAllByOrderByLastModificationDateDesc(): List<RecipeView>
}
