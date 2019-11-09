package io.cookma.recipe.application.query

import com.fasterxml.jackson.annotation.JsonIgnore

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class RecipeView(

//        @Id
//        @GeneratedValue
//        @JsonIgnore
//        var id: Long? = null,
        @Id
        var recipeId: String = "",
        var name: String = "",
        var lastModificationDate: LocalDateTime? = null
)

