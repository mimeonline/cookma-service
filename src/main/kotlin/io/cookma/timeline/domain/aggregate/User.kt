package io.cookma.timeline.domain.aggregate

import javax.persistence.Embeddable

@Embeddable
class User(
        var userId: String = "",
        var usaerName: String = "",
        var avatarId: String = ""
)
