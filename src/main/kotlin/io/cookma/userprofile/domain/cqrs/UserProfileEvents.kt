package io.cookma.userprofile.domain.cqrs

import java.time.LocalDateTime
import java.util.*

data class UserProfileCreatedEvent(
        val userId: UUID,
        val firstname: String,
        val lastname: String,
        val nickname: String,
        val birthday: String,
        val email: String,
        val creationDate: LocalDateTime
)

data class UserProfileUpdatedEvent(
        val userId: UUID,
        val firstname: String,
        val lastname: String,
        val nickname: String,
        val birthday: String,
        val email: String,
        val updateDate: LocalDateTime
)

data class UserProfileDeletedEvent(
        val userId: UUID
)
