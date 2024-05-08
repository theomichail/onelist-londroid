package com.capitalone.mobile.onelist.data.user.fakeImpl

import com.capitalone.mobile.onelist.core.model.data.user.User
import com.capitalone.mobile.onelist.core.network.NetworkResponse
import com.capitalone.mobile.onelist.data.user.api.UserRepository
import kotlinx.coroutines.delay
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeUserRepository @Inject constructor() : UserRepository {
    private var user: User? = null

    override fun getUser(): User? = user

    override suspend fun fetchUser(username: String, password: String): NetworkResponse<Boolean> {
        delay(3000)
        when(username) {
            "theo" -> user = User(
                username = "theo",
                firstName = "Theo",
                lastName = "Michail",
                dateOfBirth = LocalDate.of(2001, 11, 1)
            )
            "invalid" -> {
                user = null
                return NetworkResponse(false)
            }
            else -> {
                user = User(
                    username = "other",
                    firstName = "Other",
                    lastName = "User",
                    dateOfBirth = LocalDate.of(1990, 1, 1)
                )
            }
        }

        return NetworkResponse(true)
    }
}