package io.cookma.myrecipes.application

import io.cookma.myrecipes.domain.cqrs.AddMyRecipeCommand
import io.cookma.myrecipes.domain.cqrs.CreateMyRecipesCommand
import io.cookma.myrecipes.domain.cqrs.RemoveMyRecipeCommand
import io.cookma.recipe.domain.cqrs.RecipeCreatedEvent
import io.cookma.recipe.domain.cqrs.RecipeDeletedEvent
import io.cookma.recipe.domain.cqrs.RecipeUpdatedEvent
import io.cookma.userprofile.domain.cqrs.UserProfileCreatedEvent
import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
@ProcessingGroup("MyRecipe")
class MyRecipeApplicationEventHandler {

    companion object : KLogging()

    @Autowired
    lateinit var myRecipeApplicationService: MyRecipeApplicationService

    @Autowired
    lateinit var commandGateway: CommandGateway

    @EventHandler
    fun handle(evt: UserProfileCreatedEvent) {
        commandGateway.send<CreateMyRecipesCommand>(
                CreateMyRecipesCommand(UUID.randomUUID().toString(), evt.userId, LocalDateTime.now())
        )
    }

    @EventHandler
    fun handle(evt: RecipeCreatedEvent) {
        val myRecipesView = myRecipeApplicationService.findByUserId(evt.userId).get()
        commandGateway.send<AddMyRecipeCommand>(
                AddMyRecipeCommand(
                        myRecipesView.myRecipesId,
                        evt.recipeId,
                        evt.userId,
                        evt.name,
                        evt.images[0].imageId
                )
        )
    }

    @EventHandler
    fun handle(evt: RecipeUpdatedEvent) {
        val myRecipesView = myRecipeApplicationService.findByUserId(evt.userId).get()
        commandGateway.send<RemoveMyRecipeCommand>(
                RemoveMyRecipeCommand(
                        myRecipesView.myRecipesId,
                        evt.recipeId
                )
        )
        commandGateway.send<AddMyRecipeCommand>(
                AddMyRecipeCommand(
                        myRecipesView.myRecipesId,
                        evt.recipeId,
                        evt.userId,
                        evt.name,
                        evt.images[0].imageId
                )
        )
    }

    @EventHandler
    fun handle(evt: RecipeDeletedEvent) {
        val myRecipesView = myRecipeApplicationService.findByUserId(evt.userId).get()
        commandGateway.send<RemoveMyRecipeCommand>(
                RemoveMyRecipeCommand(
                        myRecipesView.myRecipesId,
                        evt.recipeId
                )
        )
    }
}
