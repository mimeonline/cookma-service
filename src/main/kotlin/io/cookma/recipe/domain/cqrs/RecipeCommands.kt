package io.cookma.recipe.domain.cqrs


import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class CreateRecipeCommand(
        @TargetAggregateIdentifier val recipeId: UUID,
        val name: String,
        val description: String,
        val images: List<Image>,
        val expense: String,
        val meal: List<String>,
        val times: Times,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val userId: String
)

data class UpdateRecipeCommand(
        @TargetAggregateIdentifier val recipeId: UUID,
        val name: String,
        val description: String,
        val images: List<Image>,
        val expense: String,
        val meal: List<String>,
        val times: Times,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>
)

data class DeleteRecipeCommand(@TargetAggregateIdentifier val recipeId: UUID)


