package io.cookma.recipe.application

import io.cookma.recipe.domain.Ingredient
import io.cookma.recipe.domain.Preparation

data class RecipeCreateDto(
        val name: String,
        val image: String,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val userId: String
)

data class RecipeEditDto(
        val name: String,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val userId: String
)
