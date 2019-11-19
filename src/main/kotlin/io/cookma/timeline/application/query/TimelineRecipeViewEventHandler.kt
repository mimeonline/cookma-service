package io.cookma.timeline.application.query

import io.cookma.timeline.domain.cqrs.TimelineRecipeCreatedEvent
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
}
