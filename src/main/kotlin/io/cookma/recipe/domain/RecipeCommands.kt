package io.cookma.recipe.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime

data class CreateRecipeCommand(
        val recipeId: String,
        val name: String,
        val creationDate: LocalDateTime
)

data class UpdateRecipeCommand(
        @TargetAggregateIdentifier val recipeId: String,
        val name: String,
        val updateDate: LocalDateTime)

data class DeleteRecipeCommand(@TargetAggregateIdentifier val recipeId: String)
