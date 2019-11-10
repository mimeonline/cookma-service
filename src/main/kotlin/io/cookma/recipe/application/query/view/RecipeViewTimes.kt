package io.cookma.recipe.application.query.view

import javax.persistence.Embeddable

@Embeddable
class RecipeViewTimes(
        var preparation: Int,
        var cooking: Int,
        var rest: Int
)
