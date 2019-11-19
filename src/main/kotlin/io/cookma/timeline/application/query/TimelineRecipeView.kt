package io.cookma.timeline.application.query

import java.time.LocalDateTime
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class TimelineRecipeView(
        @Id
        var timelineRecipeId: String = "",
        @Embedded
        var recipeView: RecipeView = RecipeView(),
        @Embedded
        var userView: UserView = UserView(),
        var lastModificationDate: LocalDateTime? = null
)

@Embeddable
class RecipeView(
        var recipeId: String = "",
        var recipeName: String = "",
        var imageId: String = "",
        var description: String = "",
        var expense: String = "",
        var time: Int = 0
)

@Embeddable
class UserView(
        var userId: String = "",
        var userName: String = "",
        var avatarId: String = ""
)
