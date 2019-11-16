package io.cookma.recipe.domain.aggregate

import io.cookma.recipe.domain.cqrs.*
import mu.KLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.AggregateLifecycle.markDeleted
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDateTime
import javax.persistence.ElementCollection
import javax.persistence.Embedded
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
    var description: String = ""
    var creationDate: LocalDateTime? = null
    var updateDate: LocalDateTime? = null
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
        val now = LocalDateTime.now()
        requireNotNull(cmd.recipeId) { "Recipe must have a recipeId" }
        require(cmd.name.isNotEmpty()) { "Recipe must have a name" }
        recipeId = cmd.recipeId
        creationDate = now
        name = cmd.name
        description = cmd.description
        images = cmd.images.map { i -> RecipeImage(i.position, i.imageId) }
        expense = cmd.expense
        meal = cmd.meal
        recipeTimes = RecipeTimes(cmd.times.preparation, cmd.times.cooking, cmd.times.rest)
        recipeIngredients = cmd.ingredients.map { i -> RecipeIngredient(i.position, i.count, i.unit, i.name) }
        recipePreparations = cmd.preparations.map { p -> RecipePreparation(p.step, p.stepDescription) }
        userId = cmd.userId
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
        name = cmd.name
        description = cmd.description
        // TODO Verify if this is in Update also!!
        images = cmd.images.map { i -> RecipeImage(i.position, i.imageId) }
        expense = cmd.expense
        meal = cmd.meal
        recipeTimes = RecipeTimes(cmd.times.preparation, cmd.times.cooking, cmd.times.rest)
        recipeIngredients = cmd.ingredients.map { i -> RecipeIngredient(i.position, i.count, i.unit, i.name) }
        recipePreparations = cmd.preparations.map { p -> RecipePreparation(p.step, p.stepDescription) }
        updateDate = now
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
                now
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

