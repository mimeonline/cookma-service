package io.cookma.myrecipes.application.query

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.Id


@Document
class MyRecipesView(
        @Id
        @JsonIgnore
        var id: String? = null,
        var myRecipesId: String,
        var userId: String,
        var myRecipes: MutableList<MyRecipeView>,
        var lastModificationDate: LocalDateTime
)

class MyRecipeView(
        var recipeId: String,
        var userId: String,
        var name: String,
        var imageId: String
)
