package io.cookma.myrecipes.application

import io.cookma.myrecipes.domain.cqrs.CreateMyRecipesCommand
import io.cookma.userprofile.domain.cqrs.UserProfileCreatedEvent
import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class MyRecipeApplicationEventHandler {

    companion object : KLogging()

    @Autowired
    lateinit var commandGateway: CommandGateway

    @EventHandler
    fun handle(evt: UserProfileCreatedEvent) {
        commandGateway.send<CreateMyRecipesCommand>(
                CreateMyRecipesCommand(evt.userId, LocalDateTime.now())
        )
    }

}
