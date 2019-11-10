package io.cookma.myrecipes.domain.cqrs

data class CreateMyRecipesCommand(
        val userId: String
)

data class AddMyRecipeCommand(
        val recipeId: String,
        val userId: String,
        val name: String,
        val imageUrl: String
)

data class RemoveMyRecipeCommand(
        val recipeId: String
)

data class DeleteMyRecipesCommand(
        val userId: String
)
