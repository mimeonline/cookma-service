package io.cookma.myrecipes.application.query

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param

interface MyRecipeViewRepository : MongoRepository<MyRecipesView, String> {

    fun findByMyRecipesId(@Param("myRecipesId") myRecipesId: String): MyRecipesView
    fun findByUserId(@Param("userId") userId: String): MyRecipesView

}
