package io.cookma.recipe.domain.cqrs

import java.time.LocalDateTime
import java.util.*

data class RecipeCreatedEvent(
        val recipeId: UUID,
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
        val recipeId: UUID,
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
        val recipeId: UUID,
        val userId: String
)


