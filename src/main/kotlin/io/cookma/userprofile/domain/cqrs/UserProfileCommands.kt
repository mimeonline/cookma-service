package io.cookma.userprofile.domain.cqrs

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime
import java.util.*

data class CreateUserProfileCommand(
        @TargetAggregateIdentifier  val userId: UUID,
        val firstname: String,
        val lastname: String,
        val nickname: String,
        val birthday: String,
        val email: String,
        val creationDate: LocalDateTime
)

data class UpdateUserProfileCommand(
        @TargetAggregateIdentifier val userId: UUID,
        val firstname: String,
        val lastname: String,
        val nickname: String,
        val birthday: String,
        val email: String,
        val updateDate: LocalDateTime
)

data class DeleteUserProfileCommand(
        @TargetAggregateIdentifier val userId: UUID
)
