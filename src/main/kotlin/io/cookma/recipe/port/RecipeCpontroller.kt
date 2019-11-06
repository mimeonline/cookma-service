package io.cookma.recipe.port

import io.cookma.recipe.application.RecipeApplicationService
import io.cookma.recipe.application.RecipeDto
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

    @GetMapping("{id}")
    fun handle(@PathVariable("id") id : String ): RecipeDto = RecipeDto(id, "Sphagetti")

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRecipe(@RequestBody recipe: RecipeDto) = recipeApplicationService.createRecipe(recipe)
}
