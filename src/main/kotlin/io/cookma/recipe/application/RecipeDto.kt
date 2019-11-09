package io.cookma.recipe.application

import io.cookma.recipe.domain.aggregate.Ingredient
import io.cookma.recipe.domain.aggregate.Preparation

data class RecipeCreateDto(
        val name: String
)

data class RecipeEditDto(
        val name: String
)
