package io.cookma.timeline.application

import io.cookma.recipe.domain.cqrs.RecipeCreatedEvent
import io.cookma.recipe.domain.cqrs.RecipeDeletedEvent
import io.cookma.recipe.domain.cqrs.RecipeUpdatedEvent
import io.cookma.timeline.application.query.TimelineRecipeViewRepository
import io.cookma.timeline.domain.cqrs.CreateTimelineRecipeCommand
import io.cookma.timeline.domain.cqrs.DeleteTimelineRecipeCommand
import io.cookma.timeline.domain.cqrs.UpdateTimelineRecipeCommand
import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
@ProcessingGroup("TimelineRecipe")
class TimelineRecipeApplicationEventHandler {
    companion object : KLogging()

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var timelineRecipeViewRepository: TimelineRecipeViewRepository

    @EventHandler
    fun handle(event: RecipeCreatedEvent) {
        logger.info { event }
        commandGateway.send<CreateTimelineRecipeCommand>(
                CreateTimelineRecipeCommand(
                        UUID.randomUUID(),
                        event.recipeId,
                        event.name,
                        // TODO what is with multi Images
                        event.images[0].imageId,
                        event.description,
                        event.expense,
                        event.times.cooking + event.times.cooking + event.times.rest,
                        event.creationDate,
                        event.userId,
                        // TODO implement userName
                        "",
                        // TODO implemesnt userAvatarId
                        ""
                )
        )
    }

    @EventHandler
    fun handle(event: RecipeUpdatedEvent) {
        val timelineRecipeView = timelineRecipeViewRepository.findByRecipeId(event.recipeId.toString())
        commandGateway.send<UpdateTimelineRecipeCommand>(
                UpdateTimelineRecipeCommand(
                        UUID.fromString(timelineRecipeView.timelineRecipeId),
                        event.name,
                        // TODO what is with multi Images
                        event.images[0].imageId,
                        event.description,
                        event.expense,
                        event.times.cooking + event.times.cooking + event.times.rest,
                        event.updateDate,
                        // TODO implement userName ,
                        "",
                        // TODO implemesnt userAvatarId
                        ""
                )
        )
    }

    @EventHandler
    fun handle(event: RecipeDeletedEvent) {
        val timelineRecipeView = timelineRecipeViewRepository.findByRecipeId(event.recipeId.toString())
        commandGateway.send<DeleteTimelineRecipeCommand>(
                DeleteTimelineRecipeCommand(UUID.fromString(timelineRecipeView.timelineRecipeId))
        )
    }
}
