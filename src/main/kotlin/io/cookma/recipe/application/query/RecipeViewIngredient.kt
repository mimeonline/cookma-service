package io.cookma.recipe.application.query

import javax.persistence.Embeddable

@Embeddable
class RecipeViewIngredient(
        var position: Short,
        var count: Short,
        var unit: String,
        var name: String
)
