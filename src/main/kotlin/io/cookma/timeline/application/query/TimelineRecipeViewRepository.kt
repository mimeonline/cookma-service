package io.cookma.timeline.application.query

import org.springframework.data.jpa.repository.JpaRepository

interface TimelineRecipeViewRepository : JpaRepository<TimelineRecipeView, String> {

}
