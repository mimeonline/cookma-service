package io.cookma.userprofile.domain.cqrs

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime

data class CreateUserProfileCommand(
        val userId: String,
        val firstname: String,
        val lastname: String,
        val nickname: String,
        val birthday: String,
        val email: String,
        val creationDate: LocalDateTime
)

data class UpdateUserProfileCommand(
        @TargetAggregateIdentifier val userId: String,
        val firstname: String,
        val lastname: String,
        val nickname: String,
        val birthday: String,
        val email: String,
        val updateDate: LocalDateTime
)

data class DeleteUserProfileCommand(
        @TargetAggregateIdentifier val userId: String
)
