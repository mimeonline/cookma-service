package io.cookma.recipe.domain.aggregate

import javax.persistence.Embeddable

@Embeddable
class RecipePreparation (
        var step: Short,
        var stepDescription: String
)
