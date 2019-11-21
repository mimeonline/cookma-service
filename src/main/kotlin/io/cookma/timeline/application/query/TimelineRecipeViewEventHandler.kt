package io.cookma.timeline.application.query

import io.cookma.timeline.domain.cqrs.TimelineRecipeCreatedEvent
import io.cookma.timeline.domain.cqrs.TimelineRecipeDeletedEvent
import io.cookma.timeline.domain.cqrs.TimelineRecipeUpdatedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TimelineRecipeViewEventHandler {

    @Autowired
    lateinit var timelineRecipeViewRepository: TimelineRecipeViewRepository

    @EventHandler
    fun handle(event: TimelineRecipeCreatedEvent) {
        timelineRecipeViewRepository.save(TimelineRecipeView(
                event.timelineRecipeId,
                RecipeView(
                        event.recipeId,
                        event.recipeName,
                        event.recipeDescription,
                        event.recipeImageId,
                        event.recipeExpense,
                        event.recipeTime
                ),
                UserView(
                        event.userId,
                        event.userName,
                        event.userAvatarId),
                event.creationDate)
        )
    }

    @EventHandler
    fun handle(event: TimelineRecipeUpdatedEvent) {
        val timelineRecipeView = timelineRecipeViewRepository.findByRecipeId(event.recipeId)
        timelineRecipeView.recipe.description = event.recipeDescription
        timelineRecipeView.recipe.expense = event.recipeExpense
        timelineRecipeView.recipe.imageId = event.recipeImageId
        timelineRecipeView.recipe.recipeName = event.recipeName
        timelineRecipeView.recipe.time = event.recipeTime
        timelineRecipeView.user.userName = event.userName
        timelineRecipeView.user.avatarId = event.userAvatarId
        timelineRecipeViewRepository.save(timelineRecipeView)
    }

    @EventHandler
    fun handle(event: TimelineRecipeDeletedEvent) {
        timelineRecipeViewRepository.deleteById(event.timelineRecipeId)
    }


}
