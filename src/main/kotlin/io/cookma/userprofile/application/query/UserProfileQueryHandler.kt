package io.cookma.userprofile.application.query

import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserProfileQueryHandler {
    @Autowired
    lateinit var userProfileViewRepository: UserProfileViewRepository

    @QueryHandler
    fun handle(query: FindUserProfileByUserIdQuery): UserProfileView? {
        return userProfileViewRepository.findById(query.userId).orElse(null)
    }
}
