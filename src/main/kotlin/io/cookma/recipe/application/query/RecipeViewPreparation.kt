package io.cookma.recipe.application.query

import javax.persistence.Embeddable

@Embeddable
class RecipeViewPreparation(
        var step: Short,
        var stepDescription: String
)
