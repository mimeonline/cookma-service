package io.cookma.recipe.domain.aggregate

import javax.persistence.Embeddable

class RecipeImage(
        var position: Short,
        var imageId: String
)
