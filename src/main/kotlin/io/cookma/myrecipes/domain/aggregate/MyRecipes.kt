package io.cookma.myrecipes.domain.aggregate

import io.cookma.myrecipes.domain.cqrs.*
import mu.KLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.AggregateLifecycle.markDeleted
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Id

@Aggregate
class MyRecipes {

    companion object : KLogging()

    @Id
    @AggregateIdentifier
    lateinit var myRecipesId: UUID
    lateinit var userId: UUID

    @ElementCollection
    var myRecipes: MutableList<MyRecipe> = mutableListOf()
    var creationDate: LocalDateTime? = null
    var updateDate: LocalDateTime? = null

    constructor()

    @CommandHandler
    constructor(cmd: CreateMyRecipesCommand) {
        logger.info { cmd }
        requireNotNull(cmd.myRecipesId) { "MyRecipes must have a myRecipesId" }
        requireNotNull(cmd.userId) { "MyRecipes must have a userId" }
        apply(MyRecipeCreatedEvent(cmd.myRecipesId, cmd.userId, cmd.creationDate))
    }

    @CommandHandler
    fun handle(cmd: AddMyRecipeCommand) {
        logger.info { cmd }
        val now = LocalDateTime.now()
        apply(MyRecipeAddedEvent(cmd.myRecipesId, cmd.recipeId, cmd.userId, cmd.name, cmd.imageId, now))
    }

    @CommandHandler
    fun handle(cmd: RemoveMyRecipeCommand) {
        logger.info { cmd }
        val now = LocalDateTime.now()
        apply(MyRecipeRemovedEvent(cmd.myRecipesId, cmd.recipeId, now))
    }

    @CommandHandler
    fun handle(cmd: DeleteMyRecipesCommand) {
        apply(MyRecipesDeletedEvent(cmd.userId))
    }

    @EventSourcingHandler
    fun on(evt: MyRecipeCreatedEvent) {
        myRecipesId = evt.myRecipesId
        userId = evt.userId
        creationDate = evt.creationDate
    }

    @EventSourcingHandler
    fun on(evt: MyRecipeAddedEvent) {
        updateDate = evt.updateDate
        var myRecipe = MyRecipe(evt.recipeId, evt.userId, evt.name, evt.imageId)
        myRecipes.add(myRecipe)
    }

    @EventSourcingHandler
    fun on(evt: MyRecipeRemovedEvent) {
        updateDate = evt.updateDate
        myRecipes.removeIf { it.recipeId == evt.recipeId }
    }

    @EventSourcingHandler
    fun on(evt: MyRecipesDeletedEvent) {
        markDeleted()
    }
}

