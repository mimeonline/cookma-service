package io.cookma.recipe.domain.cqrs

import io.cookma.recipe.domain.aggregate.RecipeImage
import io.cookma.recipe.domain.aggregate.RecipeIngredient
import io.cookma.recipe.domain.aggregate.RecipePreparation
import java.time.LocalDateTime

data class RecipeCreatedEvent(
        val recipeId: String,
        val name: String,
        val description: String,
        val images: List<Image>,
        val expense: String,
        val meal: List<String>,
        val times: Times,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val userId: String,
        val creationDate: LocalDateTime)

data class RecipeUpdatedEvent(
        val recipeId: String,
        val name: String,
        val description: String,
        val images: List<Image>,
        val expense: String,
        val meal: List<String>,
        val times: Times,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val updateDate: LocalDateTime,
        val userId: String)

data class RecipeDeletedEvent(
        val recipeId: String,
        val userId: String
)


