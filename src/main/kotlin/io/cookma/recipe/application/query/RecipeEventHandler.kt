package io.cookma.recipe.application.query

import io.cookma.recipe.domain.cqrs.RecipeCreatedEvent
import io.cookma.recipe.domain.cqrs.RecipeDeletedEvent
import io.cookma.recipe.domain.cqrs.RecipeUpdatedEvent
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("RecipeView")
class RecipeEventHandler {

    @Autowired
    lateinit var recipeViewRepository: RecipeViewRepository

    @EventHandler
    fun handle(event: RecipeCreatedEvent) {
        recipeViewRepository.save(RecipeView(
                null,
                event.recipeId,
                event.name,
                event.description,
                event.images.map { i -> RecipeViewImage(i.position, i.imageId) },
                event.expense,
                event.meal,
                RecipeViewTimes(event.times.preparation, event.times.cooking, event.times.rest),
                event.ingredients.map { i -> RecipeViewIngredient(i.position, i.count, i.unit, i.name) },
                event.preparations.map { p -> RecipeViewPreparation(p.step, p.stepDescription) },
                event.userId,
                event.creationDate
        ))
    }

    @EventHandler
    fun handle(event: RecipeUpdatedEvent) {
        var recipeView: RecipeView = recipeViewRepository.findByRecipeId(event.recipeId).get()
        recipeViewRepository.save(recipeView.apply {
            name = event.name
            description = event.description
            images = event.images.map { i -> RecipeViewImage(i.position, i.imageId) }
            expense = event.expense
            meal = event.meal
            times = RecipeViewTimes(event.times.preparation, event.times.cooking, event.times.rest)
            ingredients = event.ingredients.map { i -> RecipeViewIngredient(i.position, i.count, i.unit, i.name) }
            preparations = event.preparations.map { p -> RecipeViewPreparation(p.step, p.stepDescription) }
            lastModificationDate = event.updateDate
        })
    }

    @EventHandler
    fun handle(event: RecipeDeletedEvent) {
        recipeViewRepository.deleteByRecipeId(event.recipeId)
    }
}
