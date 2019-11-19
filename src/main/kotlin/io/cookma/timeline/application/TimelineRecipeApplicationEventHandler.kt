package io.cookma.timeline.application

import io.cookma.recipe.domain.cqrs.RecipeCreatedEvent
import io.cookma.timeline.domain.cqrs.CreateTimelineRecipeCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class TimelineRecipeApplicationEventHandler {

    @Autowired
    lateinit var commandGateway: CommandGateway

    @EventHandler
    fun handle(event: RecipeCreatedEvent) {
        commandGateway.send<CreateTimelineRecipeCommand>(
                CreateTimelineRecipeCommand(
                        UUID.randomUUID().toString(),
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
}
