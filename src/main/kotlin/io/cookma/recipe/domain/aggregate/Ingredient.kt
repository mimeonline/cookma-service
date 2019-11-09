package io.cookma.recipe.domain.aggregate

data class Ingredient(
        val quantity: Int,
        val unit: String,
        val ingredient: String
)
