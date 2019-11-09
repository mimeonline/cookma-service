package io.cookma.recipe.domain.aggregate

import java.util.*

data class RecipeId(val id: String)

fun createRecipeId() = RecipeId(UUID.randomUUID().toString())
