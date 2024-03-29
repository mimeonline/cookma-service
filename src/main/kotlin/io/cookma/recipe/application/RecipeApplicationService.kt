package io.cookma.recipe.application

import io.cookma.recipe.application.query.RecipeFindAllQuery
import io.cookma.recipe.application.query.RecipeFindByRecipeIdQuery
import io.cookma.recipe.application.query.RecipeView
import io.cookma.recipe.domain.cqrs.CreateRecipeCommand
import io.cookma.recipe.domain.cqrs.DeleteRecipeCommand
import io.cookma.recipe.domain.cqrs.UpdateRecipeCommand
import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
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
                        UUID.randomUUID(),
                        dto.name,
                        dto.description,
                        dto.images,
                        dto.expense,
                        dto.meal,
                        dto.times,
                        dto.ingredients,
                        dto.preparations,
                        UUID.fromString(dto.userId)
                )
        )
    }

    fun updateRecipe(recipeId: String, dto: RecipeEditDto) {
        commandGateway.send<UpdateRecipeCommand>(
                UpdateRecipeCommand(
                        UUID.fromString(recipeId),
                        dto.name,
                        dto.description,
                        dto.images,
                        dto.expense,
                        dto.meal,
                        dto.times,
                        dto.ingredients,
                        dto.preparations
                )
        )
    }

    fun deleteRecipe(recipeId: String) {
        commandGateway.send<DeleteRecipeCommand>(DeleteRecipeCommand( UUID.fromString(recipeId)))
    }


    fun findRecipeById(recipeId: String): CompletableFuture<RecipeView> {
        return queryGateway.query(RecipeFindByRecipeIdQuery(recipeId), RecipeView::class.java)
    }

    fun findAllRecipes(): Any {
        return queryGateway.query(RecipeFindAllQuery(), ResponseTypes.multipleInstancesOf(RecipeView::class.java))
    }
}
