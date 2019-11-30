package io.cookma.myrecipes.domain.cqrs

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime
import java.util.*

data class CreateMyRecipesCommand(
        @TargetAggregateIdentifier val myRecipesId: UUID,
        val userId: UUID,
        val creationDate: LocalDateTime
)

data class AddMyRecipeCommand(
        @TargetAggregateIdentifier val myRecipesId: UUID,
        val recipeId: UUID,
        val userId: UUID,
        val name: String,
        val imageId: String
)

data class RemoveMyRecipeCommand(
        @TargetAggregateIdentifier val myRecipesId: UUID,
        val recipeId: UUID
)

data class DeleteMyRecipesCommand(
        @TargetAggregateIdentifier val myRecipesId: UUID,
        val userId: UUID
)
