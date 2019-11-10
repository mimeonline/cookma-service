package io.cookma.recipe.application.query.view

import java.time.LocalDateTime
import javax.persistence.ElementCollection
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class RecipeView(

        @Id
        var recipeId: String = "",
        var name: String = "",
        var description: String = "",
        @ElementCollection
        var images: List<RecipeViewImage>? = null,
        var expense: String = "",
        @ElementCollection
        var category: List<String>? = null,
        @Embedded
        var times: RecipeViewTimes = RecipeViewTimes(0, 0, 0),
        @ElementCollection
        var ingredients: List<RecipeViewIngredient>? = null,
        @ElementCollection
        var preparations: List<RecipeViewPreparation>? = null,
        var userId: String = "",
        var lastModificationDate: LocalDateTime? = null
)

