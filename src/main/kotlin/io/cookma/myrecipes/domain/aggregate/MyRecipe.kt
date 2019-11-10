package io.cookma.myrecipes.domain.aggregate

import javax.persistence.Embeddable

@Embeddable
class MyRecipe (
    var recipeId: String,
    var userId: String,
    var name: String,
    var imageUrl: String
)
