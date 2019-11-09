package io.cookma.recipe.application

import io.cookma.recipe.application.query.RecipeFinadAllQuery
import io.cookma.recipe.application.query.RecipeFindByRecipeIdQuery
import io.cookma.recipe.application.query.RecipeView
import io.cookma.recipe.application.query.RecipeViewRepository
import io.cookma.recipe.domain.*
import io.cookma.recipe.domain.aggregate.createRecipeId
import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture

@Service
class RecipeApplicationService {

    companion object : KLogging()

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    @Autowired
    lateinit var recipeViewRepository: RecipeViewRepository

    fun createRecipe(dto: RecipeCreateDto): CompletableFuture<CreateRecipeCommand> {
        return commandGateway.send<CreateRecipeCommand>(
                CreateRecipeCommand(
                        createRecipeId().id,
                        dto.name,
                        LocalDateTime.now())
        )
    }

    fun updateRecipe(recipeId: String, dto: RecipeEditDto) {
        commandGateway.send<UpdateRecipeCommand>(UpdateRecipeCommand(
                recipeId,
                dto.name,
                LocalDateTime.now()))
    }

    fun deleteRecipe(recipeId: String) {
        commandGateway.send<DeleteRecipeCommand>(DeleteRecipeCommand(recipeId))
    }


    fun findRecipeById(recipeId: String): CompletableFuture<RecipeView> {
        return queryGateway.query(RecipeFindByRecipeIdQuery(recipeId), RecipeView::class.java)
    }

    fun findAllRecipes():  CompletableFuture<List<RecipeView>> {
        return queryGateway.query(RecipeFinadAllQuery(), ResponseTypes.multipleInstancesOf(RecipeView::class.java))
    }
}
