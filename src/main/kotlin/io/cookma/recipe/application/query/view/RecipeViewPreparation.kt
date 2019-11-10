package io.cookma.recipe.application.query.view

import javax.persistence.Embeddable

@Embeddable
class RecipeViewPreparation(
        var step: Short,
        var stepDescription: String
)
