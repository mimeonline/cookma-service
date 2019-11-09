package io.cookma.recipe.domain

import io.cookma.recipe.domain.aggregate.Recipe
import io.cookma.recipe.domain.cqrs.CreateRecipeCommand
import io.cookma.recipe.domain.cqrs.UpdateRecipeCommand
import org.springframework.stereotype.Component

@Component
class RecipeFactory {

    fun createRecipe(createRecipeCommand: CreateRecipeCommand): Recipe = Recipe(createRecipeCommand)

    fun updateRecipe(updateRecipe: UpdateRecipeCommand): Recipe = Recipe()

    fun findRecipe(recipeId: String): String = "Sphagetti"

}
