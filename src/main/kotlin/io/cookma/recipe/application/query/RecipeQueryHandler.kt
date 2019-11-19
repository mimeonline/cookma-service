package io.cookma.recipe.application.query

import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class RecipeQueryHandler {

    @Autowired
    lateinit var recipeViewRepository: RecipeViewRepository

    @QueryHandler
    fun handle(query: RecipeFindByRecipeIdQuery): RecipeView? {
        var recipe: RecipeView? = recipeViewRepository.findById(query.recipeId).orElse(null)
        return recipe
    }

    @QueryHandler
    fun handle(query: RecipeFinadAllQuery): List<RecipeView> {
        return recipeViewRepository.findAll()
    }

}
