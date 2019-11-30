package io.cookma.userprofile.application

import io.cookma.userprofile.application.query.FindUserProfileByUserIdQuery
import io.cookma.userprofile.application.query.UserProfileView
import io.cookma.userprofile.domain.cqrs.CreateUserProfileCommand
import io.cookma.userprofile.domain.cqrs.DeleteUserProfileCommand
import io.cookma.userprofile.domain.cqrs.UpdateUserProfileCommand
import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.CompletableFuture

@Service
class UserPofileApplicationService {

    companion object : KLogging()

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    fun createUserProfile(dto: UserProfileCreateDto): CompletableFuture<CreateUserProfileCommand> {
        return commandGateway.send<CreateUserProfileCommand>(
                CreateUserProfileCommand(
                        UUID.fromString(dto.userId),
                        dto.firstname,
                        dto.lastname,
                        dto.nickname,
                        dto.birthday,
                        dto.email,
                        LocalDateTime.now()
                )
        )
    }

    fun updateUserProfile(userId: String, dto: UserProfileUpdateDto) {
        commandGateway.send<UpdateUserProfileCommand>(
                UpdateUserProfileCommand(
                        UUID.fromString(userId),
                        dto.firstname,
                        dto.lastname,
                        dto.nickname,
                        dto.birthday,
                        dto.email,
                        LocalDateTime.now()
                )
        )
    }

    fun deleteUserProfile(userId: String) {
        commandGateway.send<DeleteUserProfileCommand>(
                DeleteUserProfileCommand(UUID.fromString(userId))
        )
    }

    fun findByUserId(userId: String): CompletableFuture<UserProfileView> {
        return queryGateway.query(FindUserProfileByUserIdQuery(userId), UserProfileView::class.java)
    }
}
