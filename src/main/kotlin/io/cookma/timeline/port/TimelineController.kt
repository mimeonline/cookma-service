package io.cookma.timeline.port

import io.cookma.timeline.application.TimelineApplicationService
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/timeline")
@CrossOrigin
class TimelineController {

    companion object : KLogging()

    @Autowired
    lateinit var timelineApplicationService: TimelineApplicationService

    @GetMapping("{userId}")
    fun handle(@PathVariable("userId") userId: String) = timelineApplicationService.findAllRecipes(userId)

}
