package io.cookma.recipe.application.query

import io.cookma.recipe.domain.RecipeCreatedEvent
import io.cookma.recipe.domain.RecipeDeletedEvent
import io.cookma.recipe.domain.RecipeUpdatedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RecipeEventHandler {

    @Autowired
    lateinit var recipeViewRepository: RecipeViewRepository

    @EventHandler
    fun handle(event: RecipeCreatedEvent) {
        recipeViewRepository.save(RecipeView(
                event.recipeId,
                event.name,
                event.creationDate
        ))
    }

    @EventHandler
    fun handle(event: RecipeUpdatedEvent) {
        var recipe: RecipeView = recipeViewRepository.findByRecipeId(event.recipeId)
        recipe.name = event.name
        recipe.lastModificationDate = event.updateDate
        recipeViewRepository.save(recipe)
    }

    @EventHandler
    fun handle(event: RecipeDeletedEvent) {
        recipeViewRepository.deleteByRecipeId(event.recipeId)
    }
}
