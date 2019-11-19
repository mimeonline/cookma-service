package io.cookma.timeline.port

import io.cookma.timeline.application.TimelineRecipeApplicationService
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/timeline")
@CrossOrigin
class TimelineRecipeController {

    companion object : KLogging()

    @Autowired
    lateinit var timelineRecipeApplicationService: TimelineRecipeApplicationService

    @GetMapping("{userId}")
    fun handle(@PathVariable("userId") userId: String) = timelineRecipeApplicationService.findAllRecipes(userId)

}
