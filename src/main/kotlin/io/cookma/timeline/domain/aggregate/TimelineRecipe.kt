package io.cookma.timeline.domain.aggregate


import io.cookma.timeline.domain.cqrs.*
import mu.KLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.AggregateLifecycle.markDeleted
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDateTime
import java.util.*

@Aggregate
class TimelineRecipe {

    companion object : KLogging()


    @AggregateIdentifier
    lateinit var timelineRecipeId: UUID
    var recipe: Recipe = Recipe()
    var user: User = User()
    var creationDate: LocalDateTime? = null
    var updateDate: LocalDateTime? = null

    constructor()

    @CommandHandler
    constructor(cmd: CreateTimelineRecipeCommand) {
        logger.info { cmd }
        apply(TimelineRecipeCreatedEvent(
                cmd.timelineRecipeId,
                cmd.recipeId,
                cmd.recipeName,
                cmd.recipeDescription,
                cmd.recipeImageId,
                cmd.recipeExpense,
                cmd.recipeTime,
                cmd.userId,
                cmd.userName,
                cmd.userAvatarId,
                cmd.recipeLastmodificationDate
        ))
    }

    @CommandHandler
    fun handle(cmd: UpdateTimelineRecipeCommand) {
        logger.info { cmd }
        apply(TimelineRecipeUpdatedEvent(
                cmd.timelineRecipeId,
                UUID.fromString(recipe.recipeId),
                cmd.recipeName,
                cmd.recipeImageId,
                cmd.recipeDescription,
                cmd.recipeExpense,
                cmd.recipeTime,
                UUID.fromString(user.userId),
                cmd.userName,
                cmd.userAvatarId,
                cmd.recipeLastmodificationDate
        ))
    }

    @CommandHandler
    fun handle(cmd: DeleteTimelineRecipeCommand) {
        logger.info { cmd }
        apply(TimelineRecipeDeletedEvent(cmd.timelineRecipeId))
    }

    @EventSourcingHandler
    fun on(evt: TimelineRecipeCreatedEvent) {
        timelineRecipeId = evt.timelineRecipeId
        recipe = Recipe(
                evt.recipeId.toString(),
                evt.recipeName,
                evt.recipeImageId,
                evt.recipeDescription,
                evt.recipeExpense,
                evt.recipeTime
        )
        user = User(evt.userId.toString(), evt.userName, evt.userAvatarId)
        creationDate = evt.creationDate
    }

    @EventSourcingHandler
    fun on(evt: TimelineRecipeUpdatedEvent) {
        recipe.apply {
            recipeName = evt.recipeName
            imageId = evt.recipeImageId
            description = evt.recipeDescription
            expense = evt.recipeExpense
            time = evt.recipeTime
        }
        user.apply {
            usaerName = evt.userName
            avatarId = evt.userAvatarId
        }
        updateDate = evt.updateDate
    }

    @EventSourcingHandler
    fun on(evt: TimelineRecipeDeletedEvent) {
        markDeleted()
    }
}
