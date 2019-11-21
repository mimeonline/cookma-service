package io.cookma.timeline.domain.cqrs

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime

data class CreateTimelineRecipeCommand(
        val timelineRecipeId: String,
        val recipeId: String,
        val recipeName: String,
        val recipeImageId: String,
        val recipeDescription: String,
        val recipeExpense: String,
        val recipeTime: Int,
        val recipeLastmodificationDate: LocalDateTime,
        val userId: String,
        val userName: String,
        val userAvatarId: String
)

data class UpdateTimelineRecipeCommand(
        @TargetAggregateIdentifier val timelineRecipeId: String
        val recipeName: String,
        val recipeImageId: String,
        val recipeDescription: String,
        val recipeExpense: String,
        val recipeTime: Int,
        val recipeLastmodificationDate: LocalDateTime,
        val userName: String,
        val userAvatarId: String
)

data class DeleteTimelineRecipeCommand(
        @TargetAggregateIdentifier   val timelineRecipeId: String
)
