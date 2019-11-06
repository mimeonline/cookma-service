package io.cookma.recipe.domain

import org.springframework.stereotype.Component

@Component
class RecipeFactory {

    fun createRecipe(createRecipeCommand: CreateRecipeCommand): Recipe = Recipe(createRecipeCommand)

    fun updateRecipe(updateRecipe: UpdateRecipeCommand): Recipe = Recipe()

    fun findRecipe(recipeId: String): String = "Sphagetti"

}
