package io.cookma.recipe.application.query

import javax.persistence.Embeddable

@Embeddable
class RecipeViewImage (
    var position: Short,
    var imageId: String
)
