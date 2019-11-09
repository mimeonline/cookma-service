package io.cookma.recipe.domain.cqrs


import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime

data class CreateRecipeCommand(
        val recipeId: String,
        val name: String,
        val description: String,
        val images: List<Image>,
        val expense: String,
        val category: List<String>,
        val times: Times,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val userId: String,
        val creationDate: LocalDateTime
)

data class UpdateRecipeCommand(
        @TargetAggregateIdentifier val recipeId: String,
        val name: String,
        val description: String,
        val images: List<Image>,
        val expense: String,
        val category: List<String>,
        val times: Times,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val updateDate: LocalDateTime)

data class DeleteRecipeCommand(@TargetAggregateIdentifier val recipeId: String)


