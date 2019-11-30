package io.cookma.recipe.domain.aggregate

import javax.persistence.Embeddable

class RecipePreparation (
        var step: Short,
        var stepDescription: String
)
