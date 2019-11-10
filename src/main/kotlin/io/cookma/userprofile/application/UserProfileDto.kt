package io.cookma.userprofile.application

data class UserProfileCreateDto(
        val userId: String,
        val firstname: String,
        val lastname: String,
        val nickname: String,
        val birthday: String,
        val email: String
)

data class UserProfileUpdateDto(
        val firstname: String,
        val lastname: String,
        val nickname: String,
        val birthday: String,
        val email: String
)
