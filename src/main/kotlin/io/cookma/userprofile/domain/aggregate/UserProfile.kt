package io.cookma.userprofile.domain.aggregate

import io.cookma.userprofile.domain.cqrs.*
import mu.KLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateLifecycle.markDeleted
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalQueries.localDate
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id

@Aggregate
@Entity
class UserProfile {

    companion object : KLogging()

    @AggregateIdentifier
    @Id
    var userId: String? = null
    var firstname: String = ""
    var lastname: String = ""
    var nickname: String = ""
    var birthday: LocalDate? = null
    @Embedded
    var email: Email = Email("")
    var creationDate: LocalDateTime? = null
    var updateDate: LocalDateTime? = null

    constructor()

    @CommandHandler
    constructor(cmd: CreateUserProfileCommand) {
        userId = cmd.userId
        firstname = cmd.firstname
        lastname = cmd.lastname
        nickname = cmd.nickname
        val birthdayDateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        birthday = LocalDate.parse(cmd.birthday, birthdayDateFormater)
        email = Email(cmd.email)
        creationDate = cmd.creationDate
        AggregateLifecycle.apply(UserProfileCreatedEvent(
                cmd.userId, cmd.firstname, cmd.lastname, cmd.nickname, cmd.birthday, cmd.email, cmd.creationDate
        ))
    }

    @CommandHandler
    fun handle(cmd: UpdateUserProfileCommand) {
        firstname = cmd.firstname
        lastname = cmd.lastname
        nickname = cmd.nickname
        val birthdayDateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        birthday = LocalDate.parse(cmd.birthday, birthdayDateFormater)
        email = Email(cmd.email)
        updateDate = cmd.updateDate
        AggregateLifecycle.apply(UserProfileUpdatedEvent(
                cmd.userId, cmd.firstname, cmd.lastname, cmd.nickname, cmd.birthday, cmd.email, cmd.updateDate
        ))
    }

    @CommandHandler
    fun handle(cmd: DeleteUserProfileCommand) {
        markDeleted()
        AggregateLifecycle.apply(cmd.userId)
    }
}
