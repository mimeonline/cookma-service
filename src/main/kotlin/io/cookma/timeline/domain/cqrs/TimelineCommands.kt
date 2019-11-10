package io.cookma.timeline.domain.cqrs

import java.time.LocalDateTime

data class CreateTimelineRecipeCommand(
        val timelineRecipeId: String,
        val recipeId: String,
        val recipeName: String,
        val recipeImageId: String,
        val recipeDescription: String,
        val recipeExpense: String,
        val recipeTime: Int,
        val recipeCreated: LocalDateTime,
        val userId: String,
        val userName: String,
        val userAvatarId: String
)

data class UpdateTimelineRecipeCommand(
        val timelineRecipeId: String
)

data class DeleteTimelineRecipeCommand(
        val timelineRecipeId: String
)
