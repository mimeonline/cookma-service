package io.cookma.recipe.application

import io.cookma.recipe.domain.cqrs.*


data class RecipeCreateDto(
        val name: String,
        val description: String,
        val images: List<Image>,
        val expense: String,
        val meal: List<String>,
        val times: Times,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val userId: String
)

data class RecipeEditDto(
        val name: String,
        val description: String,
        val images: List<Image>,
        val expense: String,
        val meal: List<String>,
        val times: Times,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val userId: String
)

