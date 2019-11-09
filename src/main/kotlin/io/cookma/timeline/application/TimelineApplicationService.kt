package io.cookma.timeline.application

import io.cookma.recipe.domain.cqrs.CreateRecipeCommand
import io.cookma.recipe.domain.cqrs.UpdateRecipeCommand
import org.springframework.stereotype.Service

@Service
class TimelineApplicationService {

    fun findAllRecipes(userId: String): Any {
        throw RuntimeException("Not implemented yet!")
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
