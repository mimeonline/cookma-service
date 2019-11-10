package io.cookma.recipe.application

import io.cookma.recipe.application.query.RecipeFinadAllQuery
import io.cookma.recipe.application.query.RecipeFindByRecipeIdQuery
import io.cookma.recipe.application.query.RecipeView
import io.cookma.recipe.domain.aggregate.createRecipeId
import io.cookma.recipe.domain.cqrs.CreateRecipeCommand
import io.cookma.recipe.domain.cqrs.DeleteRecipeCommand
import io.cookma.recipe.domain.cqrs.UpdateRecipeCommand
import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class RecipeApplicationService {

    companion object : KLogging()

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    fun createRecipe(dto: RecipeCreateDto): CompletableFuture<CreateRecipeCommand> {
        return commandGateway.send<CreateRecipeCommand>(
                CreateRecipeCommand(
                        createRecipeId().id,
                        dto.name,
                        dto.description,
                        dto.images,
                        dto.expense,
                        dto.category,
                        dto.times,
                        dto.ingredients,
                        dto.preparations,
                        dto.userId
                )
        )
    }

    fun updateRecipe(recipeId: String, dto: RecipeEditDto) {
        commandGateway.send<UpdateRecipeCommand>(
                UpdateRecipeCommand(
                        recipeId,
                        dto.name,
                        dto.description,
                        dto.images,
                        dto.expense,
                        dto.category,
                        dto.times,
                        dto.ingredients,
                        dto.preparations
                )
        )
    }

    fun deleteRecipe(recipeId: String) {
        commandGateway.send<DeleteRecipeCommand>(DeleteRecipeCommand(recipeId))
    }


    fun findRecipeById(recipeId: String): CompletableFuture<RecipeView> {
        return queryGateway.query(RecipeFindByRecipeIdQuery(recipeId), RecipeView::class.java)
    }

    fun findAllRecipes(): CompletableFuture<List<RecipeView>> {
        return queryGateway.query(RecipeFinadAllQuery(), ResponseTypes.multipleInstancesOf(RecipeView::class.java))
    }
}
