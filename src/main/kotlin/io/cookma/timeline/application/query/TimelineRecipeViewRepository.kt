package io.cookma.timeline.application.query

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param

interface TimelineRecipeViewRepository : MongoRepository<TimelineRecipeView, String> {

    fun findByTimelineRecipeId(@Param("timelineRecipeId") timelineRecipeId: String): TimelineRecipeView

    @Query("{'recipe.recipeId': ?0}")
    fun findByRecipeId(@Param("recipeId") recipeId: String): TimelineRecipeView

    fun deleteTimelineRecipeViewByTimelineRecipeId(@Param("timelineRecipeId") timelineRecipeId: String)

    fun findAllByOrderByLastModificationDateDesc(): List<TimelineRecipeView>
}
