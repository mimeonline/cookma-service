package io.cookma.recipe.domain.aggregate

import io.cookma.recipe.domain.*
import mu.KLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.AggregateLifecycle.markDeleted
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Aggregate
@Entity
class Recipe {

    companion object : KLogging()

    @AggregateIdentifier
    @Id
    var recipeId: String = ""
    var name: String = ""
    var creationDate: LocalDateTime? = null
    var updateDate: LocalDateTime? = null

    constructor()

    @CommandHandler
    constructor(cmd: CreateRecipeCommand) {
        logger.info { cmd }
        requireNotNull(cmd.recipeId) { "Recipe must have a recipeId" }
        require(cmd.name.isNotEmpty()) { "Recipe must have a name"}
        recipeId = cmd.recipeId
        creationDate = cmd.creationDate
        name = cmd.name
        apply(RecipeCreatedEvent(
                cmd.recipeId,
                cmd.name,
                cmd.creationDate))
    }

    @CommandHandler
    fun handle(cmd: UpdateRecipeCommand) {
        logger.info { cmd }
        name = cmd.name
        updateDate = cmd.updateDate
        apply(RecipeUpdatedEvent(
                cmd.recipeId,
                cmd.name,
                cmd.updateDate
        ))
    }

    @CommandHandler
    fun handle(cmd: DeleteRecipeCommand) {
        logger.info { cmd }
        apply(RecipeDeletedEvent(
                cmd.recipeId
        ))
        markDeleted()
    }
}
