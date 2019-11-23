package io.cookma.userprofile.application.query

import org.springframework.data.mongodb.repository.MongoRepository

interface UserProfileViewRepository : MongoRepository<UserProfileView, String> {}
