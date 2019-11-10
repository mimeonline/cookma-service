package io.cookma.myrecipes.domain.cqrs

import io.cookma.myrecipes.domain.aggregate.MyRecipe


data class MyRecipeCreatedEvent(
        val userId: String
)

data class MyRecipeAddedEvent(
        val myRecipe: MyRecipe
)

data class MyRecipeRemovedEvent(
        val recipeId: String
)

data class MyRecipesDeletedEvent(
        val userId: String
)
