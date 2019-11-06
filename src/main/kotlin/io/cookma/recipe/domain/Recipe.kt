package io.cookma.recipe.domain

import mu.KLogging
import java.time.LocalDateTime

class Recipe {

    companion object : KLogging()

    var recipeId: String? = null
    var creationDate: LocalDateTime? = null
    var userProfileId = ""

    constructor()
}
