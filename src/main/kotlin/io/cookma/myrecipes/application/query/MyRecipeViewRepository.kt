package io.cookma.myrecipes.application.query

import io.cookma.myrecipes.application.query.view.MyRecipesView
import io.cookma.recipe.application.query.view.RecipeView
import org.springframework.data.jpa.repository.JpaRepository

interface MyRecipeViewRepository : JpaRepository<MyRecipesView, String> {

}
