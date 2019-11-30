package io.cookma.myrecipes.domain.aggregate

import java.util.*
import javax.persistence.Embeddable

@Embeddable
class MyRecipe (
        var recipeId: UUID,
        var userId: UUID,
        var name: String,
        var imageId: String
)
