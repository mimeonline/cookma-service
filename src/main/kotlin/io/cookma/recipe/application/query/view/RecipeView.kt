package io.cookma.recipe.application.query.view

import java.time.LocalDateTime
import javax.persistence.*

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

@Embeddable
class RecipeViewImage(
        var position: Short,
        var imageId: String
)

@Embeddable
class RecipeViewIngredient(
        var position: Short,
        var count: Short,
        var unit: String,
        var name: String
)

@Embeddable
class RecipeViewPreparation(
        var step: Short,
        var stepDescription: String
)

@Embeddable
class RecipeViewTimes(
        var preparation: Int,
        var cooking: Int,
        var rest: Int
)
