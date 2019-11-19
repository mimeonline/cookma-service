package io.cookma.timeline.application

import io.cookma.recipe.domain.cqrs.CreateRecipeCommand
import io.cookma.recipe.domain.cqrs.UpdateRecipeCommand
import io.cookma.timeline.application.query.TimelineRecipeFinadAllQuery
import io.cookma.timeline.application.query.TimelineRecipeView
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TimelineRecipeApplicationService {

    @Autowired
    lateinit var queryGateway: QueryGateway

    fun findAllRecipes(userId: String): Any {
        return queryGateway.query(TimelineRecipeFinadAllQuery(userId), ResponseTypes.multipleInstancesOf(TimelineRecipeView::class.java))
    }

    fun createTimelineRecipe(createRecipeCommand: CreateRecipeCommand) {
        throw RuntimeException("Not implemented yet!")
    }

    fun updateTimelineRecipe(updateRecipeCommand: UpdateRecipeCommand) {
        throw RuntimeException("Not implemented yet!")
    }

    fun deleteTimelineRecipe(recipeId: String) {
        throw RuntimeException("Not implemented yet!")
    }

}
