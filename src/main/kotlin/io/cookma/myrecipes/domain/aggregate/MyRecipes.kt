package io.cookma.myrecipes.domain.aggregate

import io.cookma.myrecipes.domain.cqrs.*
import mu.KLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.AggregateLifecycle.markDeleted
import org.axonframework.spring.stereotype.Aggregate
import javax.persistence.Entity

@Aggregate
@Entity
class MyRecipes {

    companion object : KLogging()

    var userId: String? = null
    var myRecipes: MutableList<MyRecipe> = mutableListOf()

    constructor()

    @CommandHandler
    constructor(cmd: CreateMyRecipesCommand) {
        requireNotNull(cmd.userId) { "MyRecipes must have a userId" }
        userId = cmd.userId
        apply(MyRecipeCreatedEvent(cmd.userId))
    }

    @CommandHandler
    fun handle(cmd: AddMyRecipeCommand) {
        var myRecipe = MyRecipe(cmd.recipeId, cmd.userId, cmd.name, cmd.imageUrl)
        myRecipes.add(myRecipe)
        apply(MyRecipeAddedEvent(myRecipe))
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

