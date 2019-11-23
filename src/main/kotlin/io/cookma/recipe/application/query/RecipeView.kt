package io.cookma.recipe.application.query

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.Id

@Document
class RecipeView(

        @Id
        @JsonIgnore
        var id: String? = null,
        var recipeId: String = "",
        var name: String = "",
        var description: String = "",
        var images: List<RecipeViewImage>? = null,
        var expense: String = "",
        var meal: List<String>? = null,
        var times: RecipeViewTimes = RecipeViewTimes(0, 0, 0),
        var ingredients: List<RecipeViewIngredient>? = null,
        var preparations: List<RecipeViewPreparation>? = null,
        var userId: String = "",
        var lastModificationDate: LocalDateTime? = null,
        var rating: Rating = Rating(0, 0),
        var like: Like = Like(0, 0),
        var user: UserProfileRecipeView = UserProfileRecipeView("", "Michael", "https://cdn.pixabay.com/photo/2017/01/31/19/07/avatar-2026510_1280.png")

)

class RecipeViewImage(
        var position: Short = 1,
        var imageId: String = "statics/images/food-1932466_640.jpg"
)

class RecipeViewIngredient(
        var position: Short,
        var count: Short,
        var unit: String,
        var name: String
)

class RecipeViewPreparation(
        var step: Short,
        var stepDescription: String
)

class RecipeViewTimes(
        var preparation: Int,
        var cooking: Int,
        var rest: Int
)

class Rating(
        var valueRatings: Int,
        var countRatings: Int
)

class Like(
        var valueLikes: Int,
        var countLikes: Int
)

class UserProfileRecipeView(
        var userProfileId: String,
        var userName: String = "",
        var avatar: String = ""
)
