package io.cookma.myrecipes.domain.cqrs

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime

data class CreateMyRecipesCommand(
        @TargetAggregateIdentifier val myRecipesId: String,
        val userId: String,
        val creationDate: LocalDateTime
)

data class AddMyRecipeCommand(
        @TargetAggregateIdentifier val myRecipesId: String,
        val recipeId: String,
        val userId: String,
        val name: String,
        val imageId: String
)

data class RemoveMyRecipeCommand(
        @TargetAggregateIdentifier val myRecipesId: String,
        val recipeId: String
)

data class DeleteMyRecipesCommand(
        @TargetAggregateIdentifier val myRecipesId: String,
        val userId: String
)
