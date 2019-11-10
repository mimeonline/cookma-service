package io.cookma.myrecipes.application.query.view

import java.time.LocalDateTime
import javax.persistence.ElementCollection
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class MyRecipesView(
        @Id
        var userId: String,
        @ElementCollection
        var myRecipes: List<MyRecipeView>,
        var lastModificationDate: LocalDateTime
)

@Embeddable
class MyRecipeView(
        var recipeId: String,
        var userId: String,
        var name: String,
        var imageUrl: String
)
