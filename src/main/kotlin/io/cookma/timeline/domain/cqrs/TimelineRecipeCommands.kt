package io.cookma.timeline.domain.cqrs

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime
import java.util.*

data class CreateTimelineRecipeCommand(
        @TargetAggregateIdentifier val timelineRecipeId: UUID,
        val recipeId: UUID,
        val recipeName: String,
        val recipeImageId: String,
        val recipeDescription: String,
        val recipeExpense: String,
        val recipeTime: Int,
        val recipeLastmodificationDate: LocalDateTime,
        val userId: UUID,
        val userName: String,
        val userAvatarId: String
)

data class UpdateTimelineRecipeCommand(
        @TargetAggregateIdentifier val timelineRecipeId: UUID,
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
        @TargetAggregateIdentifier   val timelineRecipeId: UUID
)
