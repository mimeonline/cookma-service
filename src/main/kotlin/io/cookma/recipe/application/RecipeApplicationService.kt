package io.cookma.recipe.application

import io.cookma.recipe.domain.*
import io.cookma.timeline.application.TimelineApplicationService
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecipeApplicationService {

    companion object : KLogging()

    @Autowired
    lateinit var recipeFactory: RecipeFactory

    @Autowired
    lateinit var recipeService: RecipeService

    @Autowired
    lateinit var timelineApplicationService: TimelineApplicationService

    fun createRecipe(recipe: RecipeCreateDto) {
        val image = CmdImage("123", "JPG")
       val createRecipeCommand =  CreateRecipeCommand(
                createRecipeId().id,
                recipe.userId,
                recipe.name,
                image,
                recipe.effort,
                recipe.category,
                recipe.nutrition,
                recipe.preparationTime,
                recipe.restTime,
                recipe.ingredients,
                recipe.preparations)
        val recipe = recipeFactory.createRecipe(createRecipeCommand)
        recipe.save()
        timelineApplicationService.createTimelineRecipe(createRecipeCommand)
    }

    fun updateRecipe(recipeId: String, recipe: RecipeEditDto) {
       val updateRecipeCommand =  UpdateRecipeCommand(
                recipeId,
                recipe.name,
                recipe.effort,
                recipe.category,
                recipe.nutrition,
                recipe.preparationTime,
                recipe.restTime,
                recipe.ingredients,
                recipe.preparations)
        val recipe = recipeFactory.updateRecipe(updateRecipeCommand)
        recipe.update()
        timelineApplicationService.updateTimelineRecipe(updateRecipeCommand)
    }

    fun findRecipe(recipeId: String): String {
        return recipeFactory.findRecipe(recipeId)
    }

    fun deleteRecipe(recipeId: String) {
        recipeService.deleteRecipe(recipeId)
        timelineApplicationService.deleteTimelineRecipe(recipeId)
    }
}
