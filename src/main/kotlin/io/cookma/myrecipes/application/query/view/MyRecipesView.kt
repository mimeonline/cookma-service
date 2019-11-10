package io.cookma.myrecipes.application.query.view

import java.time.LocalDateTime
import javax.persistence.Entity

@Entity
class MyRecipesView(
        var userId: String,
        var myRecipes: List<MyRecipeView>,
        var lastModificationDate: LocalDateTime
)


class MyRecipeView(
        var recipeId: String,
        var userId: String,
        var name: String,
        var imageUrl: String
)
