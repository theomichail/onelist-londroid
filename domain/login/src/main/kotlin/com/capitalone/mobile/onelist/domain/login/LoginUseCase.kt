package com.capitalone.mobile.onelist.domain.login

import com.capitalone.mobile.onelist.data.user.api.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, password: String) =
        userRepository.fetchUser(username, password).data
}