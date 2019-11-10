package io.cookma.myrecipes.application.query

import io.cookma.myrecipes.domain.cqrs.*
import io.cookma.recipe.domain.cqrs.RecipeCreatedEvent
import mu.KLogging
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MyRecipeViewEventHandler {

    companion object : KLogging()

    @Autowired
    lateinit var myRecipeViewRepository: MyRecipeViewRepository

    @EventHandler
    fun handle(evt: MyRecipeCreatedEvent) {
        myRecipeViewRepository.save(MyRecipesView(evt.myRecipesId, evt.userId, mutableListOf(), evt.creationDate))
    }

    @EventHandler
    fun handle(evt: MyRecipeAddedEvent) {
        val myRecipesView = myRecipeViewRepository.findById(evt.myRecipesId).get()
        myRecipesView.myRecipes.add(MyRecipeView(evt.recipeId, evt.userId, evt.name, evt.imageId))
        myRecipesView.lastModificationDate = evt.updateDate
        myRecipeViewRepository.save(myRecipesView)
    }

    @EventHandler
    fun handle(evt: MyRecipeRemovedEvent) {
        val myRecipesView = myRecipeViewRepository.findById(evt.myRecipesId).get()
        myRecipesView.myRecipes.removeIf { it.recipeId == evt.recipeId }
        myRecipesView.lastModificationDate = evt.updateDate
        myRecipeViewRepository.save(myRecipesView)
    }

}
