package io.cookma.recipe.port

import io.cookma.recipe.application.RecipeApplicationService
import io.cookma.recipe.application.RecipeCreateDto
import io.cookma.recipe.application.RecipeEditDto
import io.cookma.recipe.application.query.RecipeView
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipes")
@CrossOrigin
class RecipeController {

    companion object : KLogging()

    @Autowired
    lateinit var recipeApplicationService: RecipeApplicationService


    @GetMapping("")
    fun findAllRecipes() = recipeApplicationService.findAllRecipes()

    @GetMapping("{recipeId}")
    fun findRecipeById(@PathVariable("recipeId") recipeId: String) = recipeApplicationService.findRecipeById(recipeId)


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRecipe(@RequestBody recipe: RecipeCreateDto) = recipeApplicationService.createRecipe(recipe)

    @PatchMapping("{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateRecipe(@PathVariable("recipeId") recipeId: String, @RequestBody recipe: RecipeEditDto) = recipeApplicationService.updateRecipe(recipeId, recipe)

    @DeleteMapping("{recipeId}")
    fun deleteRecipe(@PathVariable("recipeId") recipeId: String) = recipeApplicationService.deleteRecipe(recipeId)
}
