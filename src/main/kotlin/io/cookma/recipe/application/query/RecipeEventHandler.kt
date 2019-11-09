package io.cookma.recipe.application.query

import io.cookma.recipe.domain.cqrs.RecipeCreatedEvent
import io.cookma.recipe.domain.cqrs.RecipeDeletedEvent
import io.cookma.recipe.domain.cqrs.RecipeUpdatedEvent
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
                event.description,
                event.images.map { i -> RecipeViewImage(i.position, i.imageId) },
                event.expense,
                event.category,
                RecipeViewTimes(event.times.preparation, event.times.cooking, event.times.rest),
                event.ingredients.map { i -> RecipeViewIngredient(i.position, i.count, i.unit, i.name) },
                event.preparations.map { p -> RecipeViewPreparation(p.step, p.stepDescription) },
                event.userId,
                event.creationDate
        ))
    }

    @EventHandler
    fun handle(event: RecipeUpdatedEvent) {
        var recipeView: RecipeView = recipeViewRepository.findByRecipeId(event.recipeId)
        recipeView.name = event.name
        recipeView.description = event.description
        recipeView.images = event.images.map { i -> RecipeViewImage(i.position, i.imageId) }
        recipeView.expense = event.expense
        recipeView.category = event.category
        recipeView.times = RecipeViewTimes(event.times.preparation, event.times.cooking, event.times.rest)
        recipeView.ingredients = event.ingredients.map { i -> RecipeViewIngredient(i.position, i.count, i.unit, i.name) }
        recipeView.preparations = event.preparations.map { p -> RecipeViewPreparation(p.step, p.stepDescription) }
        recipeView.lastModificationDate = event.updateDate
        recipeViewRepository.save(recipeView)
    }

    @EventHandler
    fun handle(event: RecipeDeletedEvent) {
        recipeViewRepository.deleteByRecipeId(event.recipeId)
    }
}
