package io.cookma.userprofile.domain.aggregate

import io.cookma.userprofile.domain.cqrs.*
import mu.KLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.AggregateLifecycle.markDeleted
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.persistence.Embedded
import javax.persistence.Id

@Aggregate
class UserProfile {

    companion object : KLogging()

    @Id
    @AggregateIdentifier
    lateinit var userId: UUID

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
        logger.info { cmd }
        apply(UserProfileCreatedEvent(
                cmd.userId, cmd.firstname, cmd.lastname, cmd.nickname, cmd.birthday, cmd.email, cmd.creationDate
        ))
    }

    @CommandHandler
    fun handle(cmd: UpdateUserProfileCommand) {
        logger.info { cmd }
        apply(UserProfileUpdatedEvent(
                cmd.userId, cmd.firstname, cmd.lastname, cmd.nickname, cmd.birthday, cmd.email, cmd.updateDate
        ))
    }

    @CommandHandler
    fun handle(cmd: DeleteUserProfileCommand) {
        logger.info { cmd }
        apply(UserProfileDeletedEvent(cmd.userId))
    }

    @EventSourcingHandler
    fun on(evt: UserProfileCreatedEvent) {
        userId = evt.userId
        firstname = evt.firstname
        lastname = evt.lastname
        nickname = evt.nickname
        val birthdayDateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        birthday = LocalDate.parse(evt.birthday, birthdayDateFormater)
        email = Email(evt.email)
        creationDate = evt.creationDate
    }

    @EventSourcingHandler
    fun on(evt: UserProfileUpdatedEvent) {
        firstname = evt.firstname
        lastname = evt.lastname
        nickname = evt.nickname
        val birthdayDateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        birthday = LocalDate.parse(evt.birthday, birthdayDateFormater)
        email = Email(evt.email)
        updateDate = evt.updateDate
    }

    @EventSourcingHandler
    fun on(evt: DeleteUserProfileCommand) {
        markDeleted()
    }
}
