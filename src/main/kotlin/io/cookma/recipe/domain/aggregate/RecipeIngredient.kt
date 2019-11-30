package io.cookma.recipe.domain.aggregate

import javax.persistence.Embeddable

class RecipeIngredient(
        var position: Short,
        var count: Short,
        var unit: String,
        var name: String
)
