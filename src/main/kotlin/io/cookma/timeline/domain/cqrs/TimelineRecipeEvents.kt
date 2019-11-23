package io.cookma.timeline.domain.cqrs

import java.time.LocalDateTime

data class TimelineRecipeCreatedEvent(
        val timelineRecipeId: String,
        val recipeId: String,
        val recipeName: String,
        val recipeImageId: String,
        val recipeDescription: String,
        val recipeExpense: String,
        val recipeTime: Int,
        val userId: String,
        val userName: String,
        val userAvatarId: String,
        val creationDate: LocalDateTime
)

data class TimelineRecipeUpdatedEvent(
        val timelineRecipeId: String,
        val recipeId: String,
        val recipeName: String,
        val recipeImageId: String,
        val recipeDescription: String,
        val recipeExpense: String,
        val recipeTime: Int,
        val userId: String,
        val userName: String,
        val userAvatarId: String,
        val updateDate: LocalDateTime
)

data class TimelineRecipeDeletedEvent(
        val timelineRecipeId: String
)
