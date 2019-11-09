package io.cookma.recipe.domain.cqrs

// Sub Types
data class Times(
        val preparation: Int,
        val cooking: Int,
        val rest: Int
)

data class Ingredient(
        val position: Short,
        val count: Short,
        val unit: String,
        val name: String
)

data class Preparation(
        val step: Short,
        val stepDescription: String
)

data class Image(
        val position: Short,
        val imageId: String
)
