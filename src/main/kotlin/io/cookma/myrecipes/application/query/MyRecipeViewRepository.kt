package io.cookma.myrecipes.application.query

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface MyRecipeViewRepository : JpaRepository<MyRecipesView, String> {

    fun findByUserId(@Param("userId") userId: String): MyRecipesView
}
