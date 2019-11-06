package io.cookma.recipe.domain

import mu.KLogging
import java.time.LocalDateTime

class Recipe {

    companion object : KLogging()

    var recipeId: String? = null
    var creationDate: LocalDateTime? = null
    var userProfileId = ""
    var name: String = ""
    var image: Image = Image("", "")
    var effort: String = ""
    var category: String = ""
    var nutrition: List<String> = listOf()
    var preparationTime: Int = 0
    var restTime: Int = 0
    var ingredients: List<Ingredient> = listOf()
    var preparations: List<Preparation> = listOf()

    constructor()

    constructor(createRecipeCommand: CreateRecipeCommand) {
        create(createRecipeCommand)
    }

    fun create(recipe: CreateRecipeCommand) {
        // create me
        throw RuntimeException("Not implemented yet!")
    }

    fun save() {
        throw RuntimeException("Not implemented yet!")
    }

    fun update() {
        throw RuntimeException("Not implemented yet!")
    }

}
