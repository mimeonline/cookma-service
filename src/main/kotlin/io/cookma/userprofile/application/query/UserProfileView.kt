package io.cookma.userprofile.application.query

import java.time.LocalDateTime

class UserProfileView(
        var userId: String,
        var firstname: String,
        var lastname: String,
        var nickname: String,
        var birthday: String,
        var email: String,
        var lastModificationDate: LocalDateTime
)
