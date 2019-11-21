package io.cookma.timeline.application.query

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TimelineRecipeViewRepository : JpaRepository<TimelineRecipeView, String> {

    @Query("SELECT t FROM TimelineRecipeView t WHERE t.recipe.recipeId = :recipeId")
    fun findByRecipeId(@Param("recipeId") recipeId: String): TimelineRecipeView
}
