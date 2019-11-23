package io.cookma.recipe.application.query

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface RecipeViewRepository : MongoRepository<RecipeView, String> {

    fun findByRecipeId(@Param("recipeId") recipeId: String): Optional<RecipeView>
    fun deleteByRecipeId(@Param("recipeId") recipeId: String)
    fun findAllByOrderByLastModificationDateDesc(): List<RecipeView>
}
