package io.cookma.timeline.application.query

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.Id

@Document
class TimelineRecipeView(
        @Id
        @JsonIgnore
        var id: String? = null,
        var timelineRecipeId: String = "",
        var recipe: RecipeView = RecipeView(),
        var user: UserView = UserView(),
        var lastModificationDate: LocalDateTime? = null
)

class RecipeView(
        var recipeId: String = "",
        var recipeName: String = "",
        var imageId: String = "",
        var description: String = "",
        var expense: String = "",
        var time: Int = 0
)

class UserView(
        var userId: String = "",
        var userName: String = "",
        var avatarId: String = ""
)
