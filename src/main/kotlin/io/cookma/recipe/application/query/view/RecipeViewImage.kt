package io.cookma.recipe.application.query.view

import javax.persistence.Embeddable

@Embeddable
class RecipeViewImage (
    var position: Short,
    var imageId: String
)
