package io.cookma.myrecipes.domain.aggregate

import io.cookma.myrecipes.domain.cqrs.*
import mu.KLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.AggregateLifecycle.markDeleted
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Aggregate
@Entity
class MyRecipes {

    companion object : KLogging()

    @Id
    @AggregateIdentifier
    var myRecipesId: String? = null

    var userId: String? = null

    @ElementCollection
    var myRecipes: MutableList<MyRecipe> = mutableListOf()
    var creationDate: LocalDateTime? = null
    var updateDate: LocalDateTime? = null

    constructor()

    @CommandHandler
    constructor(cmd: CreateMyRecipesCommand) {
        requireNotNull(cmd.userId) { "MyRecipes must have a userId" }
        val uuid = UUID.randomUUID().toString()
        myRecipesId = uuid
        userId = cmd.userId
        creationDate = cmd.creationDate
        apply(MyRecipeCreatedEvent(uuid, cmd.userId, cmd.creationDate))
    }

    @CommandHandler
    fun handle(cmd: AddMyRecipeCommand) {
        val now = LocalDateTime.now()
        updateDate = now
        var myRecipe = MyRecipe(cmd.recipeId, cmd.userId, cmd.name, cmd.imageId)
        myRecipes.add(myRecipe)
        apply(MyRecipeAddedEvent(cmd.myRecipesId, cmd.recipeId, cmd.userId, cmd.name, cmd.imageId, now))
    }

    @CommandHandler
    fun handle(cmd: RemoveMyRecipeCommand) {
        var index = myRecipes.indexOfFirst { it.recipeId == cmd.recipeId }
        myRecipes.removeAt(index)
        apply(MyRecipeRemovedEvent(cmd.recipeId))
    }

    @CommandHandler
    fun handle(cmd: DeleteMyRecipesCommand) {
        markDeleted()
        apply(MyRecipesDeletedEvent(cmd.userId))
    }
}

