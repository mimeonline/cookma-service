package io.cookma.userprofile.domain.aggregate

import javax.persistence.Embeddable

@Embeddable
class Email(
        var value: String
)
