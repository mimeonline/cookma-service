package io.cookma.userprofile.domain.cqrs

import java.time.LocalDateTime

data class UserProfileCreatedEvent(
        val userId: String,
        val firstname: String,
        val lastname: String,
        val nickname: String,
        val birthday: String,
        val email: String,
        val creationDate: LocalDateTime
)

data class UserProfileUpdatedEvent(
        val firstname: String,
        val lastname: String,
        val nickname: String,
        val birthday: String,
        val email: String,
        val updateDate: LocalDateTime
)

data class UserProfileDeletedEvent(
        val userId: String
)
