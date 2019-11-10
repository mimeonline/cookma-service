package io.cookma.myrecipes.port

import io.cookma.myrecipes.application.MyRecipeAddDto
import io.cookma.myrecipes.application.MyRecipeApplicationService
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/myrecipes")
@CrossOrigin
class MyRecipeController {

    companion object : KLogging()

    @Autowired
    lateinit var myRecipeApplicationService: MyRecipeApplicationService

    @GetMapping("{userId}")
    fun findByUserId(@PathVariable("userId") userId: String) = myRecipeApplicationService.findByUserId(userId)

    @PostMapping("{userId}")
    fun addRecipe(@PathVariable("userId") userId: String, @RequestBody myRecipeAddDto : MyRecipeAddDto) = myRecipeApplicationService.addRecipe(userId, myRecipeAddDto)

}
