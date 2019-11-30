package io.cookma.myrecipes.domain.cqrs

import java.time.LocalDateTime
import java.util.*


data class MyRecipeCreatedEvent(
        val myRecipesId: UUID,
        val userId: UUID,
        val creationDate: LocalDateTime
)

data class MyRecipeAddedEvent(
        val myRecipesId: UUID,
        val recipeId: UUID,
        val userId: UUID,
        val name: String,
        val imageId: String,
        val updateDate: LocalDateTime
)

data class MyRecipeRemovedEvent(
        val myRecipesId: UUID,
        val recipeId: UUID,
        val updateDate: LocalDateTime
)

data class MyRecipesDeletedEvent(
        val userId: UUID
)
