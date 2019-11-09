package io.cookma.recipe.domain

import java.time.LocalDateTime

data class RecipeCreatedEvent(
        val recipeId: String,
        val name: String,
        val creationDate: LocalDateTime)

data class RecipeUpdatedEvent(
        val recipeId: String,
        val name: String,
        val updateDate: LocalDateTime)

data class RecipeDeletedEvent(val recipeId: String)


