package io.cookma.userprofile.port

import mu.KLogging
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/timeline")
@CrossOrigin
class UserProfileController {

    companion object : KLogging()

    @GetMapping("{userId}")
    fun handle(@PathVariable("userId") id: String): String = "UserProfil"

}
