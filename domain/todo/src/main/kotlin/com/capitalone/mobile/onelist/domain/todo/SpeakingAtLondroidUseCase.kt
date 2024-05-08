package com.capitalone.mobile.onelist.domain.todo

import com.capitalone.mobile.onelist.data.user.api.UserRepository
import javax.inject.Inject

class SpeakingAtLondroidUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Boolean {
        val user = userRepository.getUser() ?: return false

        if (user.firstName.contains("theo", ignoreCase = true)) return true
        if (user.dateOfBirth.year >= 2000) return true

        return false
    }
}