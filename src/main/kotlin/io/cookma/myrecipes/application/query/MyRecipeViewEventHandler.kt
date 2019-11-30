package io.cookma.myrecipes.application.query

import io.cookma.myrecipes.domain.cqrs.MyRecipeAddedEvent
import io.cookma.myrecipes.domain.cqrs.MyRecipeCreatedEvent
import io.cookma.myrecipes.domain.cqrs.MyRecipeRemovedEvent
import mu.KLogging
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("MyRecipeView")
class MyRecipeViewEventHandler {

    companion object : KLogging()

    @Autowired
    lateinit var myRecipeViewRepository: MyRecipeViewRepository

    @EventHandler
    fun handle(evt: MyRecipeCreatedEvent) {
        myRecipeViewRepository.save(MyRecipesView(null,evt.myRecipesId.toString(), evt.userId.toString(), mutableListOf(), evt.creationDate))
    }

    @EventHandler
    fun handle(evt: MyRecipeAddedEvent) {
        val myRecipesView = myRecipeViewRepository.findByMyRecipesId(evt.myRecipesId.toString())
        myRecipesView.myRecipes.add(MyRecipeView(evt.recipeId.toString(), evt.userId.toString(), evt.name, evt.imageId))
        myRecipesView.lastModificationDate = evt.updateDate
        myRecipeViewRepository.save(myRecipesView)
    }

    @EventHandler
    fun handle(evt: MyRecipeRemovedEvent) {
        val myRecipesView = myRecipeViewRepository.findByMyRecipesId(evt.myRecipesId.toString())
        myRecipesView.myRecipes.removeIf { it.recipeId == evt.recipeId.toString() }
        myRecipesView.lastModificationDate = evt.updateDate
        myRecipeViewRepository.save(myRecipesView)
    }
}
