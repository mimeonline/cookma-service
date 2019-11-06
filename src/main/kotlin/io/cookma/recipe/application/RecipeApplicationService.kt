package io.cookma.recipe.application

import mu.KLogging
import org.springframework.stereotype.Service

@Service
class RecipeApplicationService {

    companion object : KLogging()

    fun createRecipe(recipe: RecipeDto) {
        logger.info("Hot Deploy 4")
        logger.info(recipe.toString())
        logger.info("Another")
    }
}
