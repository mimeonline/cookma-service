package io.cookma.timeline.application.query

import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TimelineRecipeViewQueryHandler {

    @Autowired
    lateinit var timelineRecipeViewRepository: TimelineRecipeViewRepository

    @QueryHandler
    fun handle(query: TimelineRecipeFinadAllQuery): List<TimelineRecipeView> {
        return timelineRecipeViewRepository.findAllByOrderByLastModificationDateDesc()
    }
}
