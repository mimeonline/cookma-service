package io.cookma.timeline.domain.cqrs

data class TimelineRecipeCreatedEvent(
        val timelineRecipeId: String
)

data class TimelineRecipeUpdatedEvent(
        val timelineRecipeId: String
)

data class TimelineRecipeDeletedEvent(
        val timelineRecipeId: String
)
