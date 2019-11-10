package io.cookma.myrecipes.application.query

import java.time.LocalDateTime
import javax.persistence.ElementCollection
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class MyRecipesView(
        @Id
        var myRecipesId: String,
        var userId: String,
        @ElementCollection
        var myRecipes: MutableList<MyRecipeView>,
        var lastModificationDate: LocalDateTime
)

@Embeddable
class MyRecipeView(
        var recipeId: String,
        var userId: String,
        var name: String,
        var imageId: String
)
