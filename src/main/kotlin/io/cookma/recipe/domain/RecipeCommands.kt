package io.cookma.recipe.domain

data class CreateRecipeCommand(
        val recipeId: String,
        val userProfileId: String,
        val name: String,
        val image: CmdImage,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>)

data class CmdImage(val imageId: String, val extension: String)


data class UpdateRecipeCommand(
        val recipeId: String,
        val name: String,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>)

data class DeleteRecipeCommand(val recipeId: String)
