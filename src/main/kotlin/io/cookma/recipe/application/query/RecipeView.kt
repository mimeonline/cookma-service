package io.cookma.recipe.application.query

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
        var meal: List<String>? = null,
        @Embedded
        var times: RecipeViewTimes = RecipeViewTimes(0, 0, 0),
        @ElementCollection
        var ingredients: List<RecipeViewIngredient>? = null,
        @ElementCollection
        var preparations: List<RecipeViewPreparation>? = null,
        var userId: String = "",
        var lastModificationDate: LocalDateTime? = null,
        @Embedded
        var rating: Rating = Rating(0, 0),
        @Embedded
        var like: Like = Like(0, 0),
        @Embedded
        var user: UserProfileRecipeView = UserProfileRecipeView("", "Michael", "https://cdn.pixabay.com/photo/2017/01/31/19/07/avatar-2026510_1280.png")

)

@Embeddable
class RecipeViewImage(
        var position: Short = 1,
        var imageId: String = "statics/images/food-1932466_640.jpg"
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

@Embeddable
class Rating(
        var valueRatings: Int,
        var countRatings: Int
)

@Embeddable
class Like(
        var valueLikes: Int,
        var countLikes: Int
)

@Embeddable
class UserProfileRecipeView(
        var userProfileId: String,
        var userName: String = "",
        var avatar: String = ""
)
