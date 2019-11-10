package io.cookma.userprofile.port

import io.cookma.userprofile.application.UserPofileApplicationService
import io.cookma.userprofile.application.UserProfileCreateDto
import io.cookma.userprofile.application.UserProfileUpdateDto
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/userprofile")
@CrossOrigin
class UserProfileController {

    companion object : KLogging()

    @Autowired
    lateinit var userPofileApplicationService: UserPofileApplicationService

    @GetMapping("{userId}")
    fun findByUserId(@PathVariable("userId") userId: String) = userPofileApplicationService.findByUserId(userId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUserProfile(@RequestBody userProfile: UserProfileCreateDto) = userPofileApplicationService.createUserProfile(userProfile)

    @PatchMapping("{userProfile}")
    fun updateuserProfile(@PathVariable("userId") userId: String, @RequestBody userProfile: UserProfileUpdateDto) =
            userPofileApplicationService.updateUserProfile(userId, userProfile)

    @DeleteMapping
    fun deleteUserProfile(@PathVariable userId: String) = userPofileApplicationService.deleteUserProfile(userId)
}
