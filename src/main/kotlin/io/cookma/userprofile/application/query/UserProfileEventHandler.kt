package io.cookma.userprofile.application.query

import io.cookma.userprofile.domain.cqrs.UserProfileCreatedEvent
import io.cookma.userprofile.domain.cqrs.UserProfileDeletedEvent
import io.cookma.userprofile.domain.cqrs.UserProfileUpdatedEvent
import mu.KLogging
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserProfileEventHandler {

    companion object : KLogging()

    @Autowired
    lateinit var userProfileViewRepository: UserProfileViewRepository

    @EventHandler
    fun handle(evt: UserProfileCreatedEvent) {
        userProfileViewRepository.save(
                UserProfileView(
                        evt.userId,
                        evt.firstname,
                        evt.lastname,
                        evt.nickname,
                        evt.birthday,
                        evt.email,
                        evt.creationDate
                )
        )
    }

    @EventHandler
    fun handle(evt: UserProfileUpdatedEvent) {
        var userProfileView: UserProfileView? = userProfileViewRepository.findById(evt.userId).orElse(null)
        if (userProfileView != null) {
            userProfileViewRepository.save(userProfileView)
        } else {
            logger.error("userProfile with userId: ${evt.userId}  not found!")
        }
    }

    @EventHandler
    fun handle(evt: UserProfileDeletedEvent) {
        userProfileViewRepository.deleteById(evt.userId)
    }
}
