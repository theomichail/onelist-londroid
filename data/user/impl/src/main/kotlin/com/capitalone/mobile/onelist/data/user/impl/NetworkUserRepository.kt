package com.capitalone.mobile.onelist.data.user.impl

import com.capitalone.mobile.onelist.core.model.data.user.User
import com.capitalone.mobile.onelist.core.network.NetworkResponse
import com.capitalone.mobile.onelist.data.user.api.UserRepository
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUserRepository @Inject constructor(
    retrofit: Retrofit
) : UserRepository {
    private val userApi = retrofit.create(UserApi::class.java)

    private var user: User? = null

    override suspend fun fetchUser(username: String, password: String): NetworkResponse<Boolean> {
        return try {
            val userDto = userApi.login(UserApi.LoginRequest(username, password))

            user = userDto.toUser()

            NetworkResponse(true)
        } catch(e: Exception) {
            user = null
            NetworkResponse(false)
        }
    }

    override fun getUser(): User? = user
}