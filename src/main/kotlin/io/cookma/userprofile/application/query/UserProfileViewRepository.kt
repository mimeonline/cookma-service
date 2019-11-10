package io.cookma.userprofile.application.query

import org.springframework.data.jpa.repository.JpaRepository

interface UserProfileViewRepository : JpaRepository<UserProfileView, String> {}
