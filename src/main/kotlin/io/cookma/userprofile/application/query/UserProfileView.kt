package io.cookma.userprofile.application.query

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class UserProfileView(

        @JsonIgnore
        var id: String? = null,
        var userId: String,
        var firstname: String,
        var lastname: String,
        var nickname: String,
        var birthday: String,
        var email: String,
        var lastModificationDate: LocalDateTime
)
