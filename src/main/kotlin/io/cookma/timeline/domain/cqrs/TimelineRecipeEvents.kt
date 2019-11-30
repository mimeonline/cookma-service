package io.cookma.timeline.domain.cqrs

import java.time.LocalDateTime
import java.util.*

data class TimelineRecipeCreatedEvent(
        val timelineRecipeId: UUID,
        val recipeId: UUID,
        val recipeName: String,
        val recipeImageId: String,
        val recipeDescription: String,
        val recipeExpense: String,
        val recipeTime: Int,
        val userId: UUID,
        val userName: String,
        val userAvatarId: String,
        val creationDate: LocalDateTime
)

data class TimelineRecipeUpdatedEvent(
        val timelineRecipeId: UUID,
        val recipeId: UUID,
        val recipeName: String,
        val recipeImageId: String,
        val recipeDescription: String,
        val recipeExpense: String,
        val recipeTime: Int,
        val userId: UUID,
        val userName: String,
        val userAvatarId: String,
        val updateDate: LocalDateTime
)

data class TimelineRecipeDeletedEvent(
        val timelineRecipeId: UUID
)
