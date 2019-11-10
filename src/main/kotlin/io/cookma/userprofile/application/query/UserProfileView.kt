package io.cookma.userprofile.application.query

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserProfileView(
        @Id
        var userId: String,
        var firstname: String,
        var lastname: String,
        var nickname: String,
        var birthday: String,
        var email: String,
        var lastModificationDate: LocalDateTime
)
