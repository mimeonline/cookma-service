package io.cookma.recipe.port

import io.cookma.recipe.application.RecipeApplicationService
import io.cookma.recipe.application.RecipeCreateDto
import io.cookma.recipe.application.RecipeEditDto
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

    @GetMapping("{recipeId}")
    fun handleGet(@PathVariable("recipeId") recipeId: String): String = recipeApplicationService.findRecipe(recipeId)


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun handleCreate(@RequestBody recipe: RecipeCreateDto) = recipeApplicationService.createRecipe(recipe)

    @PatchMapping("{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    fun handleUpdate(@PathVariable("recipeId") recipeId: String, @RequestBody recipe: RecipeEditDto) = recipeApplicationService.updateRecipe(recipeId, recipe)

    @DeleteMapping("{recipeId}")
    fun handleDelete(@PathVariable("recipeId") recipeId: String) = recipeApplicationService.deleteRecipe(recipeId)
}
