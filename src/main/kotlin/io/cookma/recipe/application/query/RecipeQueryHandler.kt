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
        return recipeViewRepository.findByRecipeId(query.recipeId).orElse(null)
    }

    @QueryHandler
    fun handle(query: RecipeFinadAllQuery): List<RecipeView> {
        var list = recipeViewRepository.findAll()
        return list
    }

}
