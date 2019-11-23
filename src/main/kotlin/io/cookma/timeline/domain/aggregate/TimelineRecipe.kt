package io.cookma.timeline.domain.aggregate


import io.cookma.timeline.domain.cqrs.*
import mu.KLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDateTime
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id

@Aggregate
@Entity
class TimelineRecipe {

    companion object : KLogging()

    @AggregateIdentifier
    @Id
    var timelineRecipeId: String = ""
    @Embedded
    var recipe: Recipe = Recipe()
    @Embedded
    var user: User = User()
    var creationDate: LocalDateTime? = null
    var updateDate: LocalDateTime? = null

    constructor()

    @CommandHandler
    constructor(cmd: CreateTimelineRecipeCommand) {
        logger.info { cmd }

        timelineRecipeId = cmd.timelineRecipeId
        recipe = Recipe(
                cmd.recipeId,
                cmd.recipeName,
                cmd.recipeImageId,
                cmd.recipeDescription,
                cmd.recipeExpense,
                cmd.recipeTime
        )
        user = User(cmd.userId, cmd.userName, cmd.userAvatarId)
        val now = LocalDateTime.now()
        creationDate = cmd.recipeLastmodificationDate
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
        recipe.recipeName = cmd.recipeName
        recipe.imageId = cmd.recipeImageId
        recipe.description = cmd.recipeDescription
        recipe.expense = cmd.recipeExpense
        recipe.time = cmd.recipeTime
        user.usaerName = cmd.userName
        user.avatarId = cmd.userAvatarId
        updateDate = cmd.recipeLastmodificationDate
        apply(TimelineRecipeUpdatedEvent(
                timelineRecipeId,
                recipe.recipeId,
                cmd.recipeName,
                cmd.recipeImageId,
                cmd.recipeDescription,
                cmd.recipeExpense,
                cmd.recipeTime,
                user.userId,
                cmd.userName,
                cmd.userAvatarId,
                cmd.recipeLastmodificationDate
        ))
    }

    @CommandHandler
    fun handle(cmd: DeleteTimelineRecipeCommand) {
        AggregateLifecycle.markDeleted()
        apply(TimelineRecipeDeletedEvent(cmd.timelineRecipeId))
    }

}
