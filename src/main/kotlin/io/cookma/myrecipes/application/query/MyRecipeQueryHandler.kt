package io.cookma.myrecipes.application.query

import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MyRecipeQueryHandler {

    @Autowired
    lateinit var myRecipeViewRepository: MyRecipeViewRepository

    @QueryHandler
    fun handle(query: FindMyRecipesByUserIdQuery): MyRecipesView {
        return myRecipeViewRepository.findByUserId(query.userId)
    }
}
