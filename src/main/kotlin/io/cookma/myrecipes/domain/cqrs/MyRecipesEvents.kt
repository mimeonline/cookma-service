package io.cookma.myrecipes.domain.cqrs

import java.time.LocalDateTime


data class MyRecipeCreatedEvent(
        val myRecipesId: String,
        val userId: String,
        val creationDate: LocalDateTime
)

data class MyRecipeAddedEvent(
        val myRecipesId: String,
        val recipeId: String,
        val userId: String,
        val name: String,
        val imageId: String,
        val updateDate: LocalDateTime
)

data class MyRecipeRemovedEvent(
        val recipeId: String
)

data class MyRecipesDeletedEvent(
        val userId: String
)
