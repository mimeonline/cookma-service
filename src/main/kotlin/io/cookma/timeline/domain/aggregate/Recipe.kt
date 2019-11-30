package io.cookma.timeline.domain.aggregate

import javax.persistence.Embeddable

@Embeddable
class Recipe (
        var recipeId: String = "",
        var recipeName: String = "",
        var imageId: String = "",
        var description: String = "",
        var expense: String = "",
        var time: Int = 0
)
