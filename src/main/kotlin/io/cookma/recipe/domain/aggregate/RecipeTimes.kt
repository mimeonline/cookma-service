package io.cookma.recipe.domain.aggregate

import javax.persistence.Embeddable

@Embeddable
class RecipeTimes(
        var preparation: Int,
        var cooking: Int,
        var rest: Int
)
