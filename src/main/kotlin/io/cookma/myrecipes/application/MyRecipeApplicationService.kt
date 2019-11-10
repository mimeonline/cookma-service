package io.cookma.myrecipes.application

import io.cookma.myrecipes.application.query.FindMyRecipesByUserIdQuery
import io.cookma.myrecipes.application.query.MyRecipesView
import io.cookma.myrecipes.domain.cqrs.AddMyRecipeCommand
import io.cookma.recipe.application.RecipeApplicationService
import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class MyRecipeApplicationService {

    companion object : KLogging()

    @Autowired
    lateinit var recipeApplicationService: RecipeApplicationService

    @Autowired
    lateinit var queryGateway: QueryGateway

    @Autowired
    lateinit var commandGateway: CommandGateway

    fun findByUserId(userId: String): CompletableFuture<MyRecipesView> {
        return queryGateway.query(FindMyRecipesByUserIdQuery(userId), MyRecipesView::class.java)
    }

    fun addRecipe(userId: String, dto: MyRecipeAddDto) {
        val myRecipesView = findByUserId(userId).get()
        val recipesView = recipeApplicationService.findRecipeById(dto.recipeId).get()
        val imageId = recipesView?.images?.get(0)?.imageId ?: "000"

        commandGateway.send<AddMyRecipeCommand>(
                AddMyRecipeCommand(
                        myRecipesView.myRecipesId,
                        dto.recipeId,
                        myRecipesView.userId,
                        recipesView.name,
                        imageId
                )
        )
    }
}
