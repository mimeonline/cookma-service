package io.cookma.recipe.domain.aggregate

import io.cookma.recipe.domain.cqrs.*
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
import javax.persistence.Embedded
import javax.persistence.Id

@Aggregate
class Recipe {

    companion object : KLogging()

    @Id
    @AggregateIdentifier
    lateinit var recipeId: UUID
    var name: String = ""
    var description: String = ""
    lateinit var creationDate: LocalDateTime
    lateinit var updateDate: LocalDateTime
    @ElementCollection
    var images: List<RecipeImage> = listOf()
    var expense: String = ""
    @ElementCollection
    var meal: List<String> = listOf()
    @Embedded
    var recipeTimes: RecipeTimes = RecipeTimes(0, 0, 0)
    @ElementCollection
    var recipeIngredients: List<RecipeIngredient> = listOf()
    @ElementCollection
    var recipePreparations: List<RecipePreparation> = listOf()
    var userId: String = ""

    constructor()

    @CommandHandler
    constructor(cmd: CreateRecipeCommand) {
        logger.info { cmd }
        requireNotNull(cmd.recipeId) { "Recipe must have a recipeId" }
        require(cmd.name.isNotEmpty()) { "Recipe must have a name" }
        val now = LocalDateTime.now()
        apply(RecipeCreatedEvent(
                cmd.recipeId,
                cmd.name,
                cmd.description,
                cmd.images,
                cmd.expense,
                cmd.meal,
                cmd.times,
                cmd.ingredients,
                cmd.preparations,
                cmd.userId,
                now))
    }

    @CommandHandler
    fun handle(cmd: UpdateRecipeCommand) {
        logger.info { cmd }
        val now = LocalDateTime.now()
        apply(RecipeUpdatedEvent(
                cmd.recipeId,
                cmd.name,
                cmd.description,
                cmd.images,
                cmd.expense,
                cmd.meal,
                cmd.times,
                cmd.ingredients,
                cmd.preparations,
                now,
                userId
        ))
    }

    @CommandHandler
    fun handle(cmd: DeleteRecipeCommand) {
        logger.info { cmd }
        apply(RecipeDeletedEvent(
                cmd.recipeId,
                userId
        ))
    }

    @EventSourcingHandler
    fun on(evt: RecipeCreatedEvent) {
        logger.info { evt }
        recipeId = evt.recipeId
        creationDate = evt.creationDate
        name = evt.name
        description = evt.description
        images = evt.images.map { i -> RecipeImage(i.position, i.imageId) }
        expense = evt.expense
        meal = evt.meal
        recipeTimes = RecipeTimes(evt.times.preparation, evt.times.cooking, evt.times.rest)
        recipeIngredients = evt.ingredients.map { i -> RecipeIngredient(i.position, i.count, i.unit, i.name) }
        recipePreparations = evt.preparations.map { p -> RecipePreparation(p.step, p.stepDescription) }
        userId = evt.userId
    }

    @EventSourcingHandler
    fun on(evt: RecipeUpdatedEvent) {
        name = evt.name
        description = evt.description
        // TODO Verify if this is in Update also!!
        images = evt.images.map { i -> RecipeImage(i.position, i.imageId) }
        expense = evt.expense
        meal = evt.meal
        recipeTimes = RecipeTimes(evt.times.preparation, evt.times.cooking, evt.times.rest)
        recipeIngredients = evt.ingredients.map { i -> RecipeIngredient(i.position, i.count, i.unit, i.name) }
        recipePreparations = evt.preparations.map { p -> RecipePreparation(p.step, p.stepDescription) }
        updateDate = evt.updateDate
    }

    @EventSourcingHandler
    fun on(evt: RecipeDeletedEvent) {
        markDeleted()
    }
}

