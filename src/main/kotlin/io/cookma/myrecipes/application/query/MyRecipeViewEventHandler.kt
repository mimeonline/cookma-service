package io.cookma.myrecipes.application.query

import io.cookma.myrecipes.domain.cqrs.AddMyRecipeCommand
import io.cookma.myrecipes.domain.cqrs.MyRecipeAddedEvent
import io.cookma.myrecipes.domain.cqrs.MyRecipeCreatedEvent
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
        logger.info { myRecipesView }
        myRecipesView.myRecipes.add(MyRecipeView(evt.recipeId, evt.userId, evt.name, evt.imageId))
        myRecipesView.lastModificationDate = evt.updateDate
        myRecipeViewRepository.save(myRecipesView)
    }

}
