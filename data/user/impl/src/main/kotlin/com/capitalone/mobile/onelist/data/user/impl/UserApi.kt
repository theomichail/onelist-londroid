package com.capitalone.mobile.onelist.data.user.impl

import com.capitalone.mobile.onelist.data.user.impl.dto.UserDto
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/user")
    suspend fun login(@Body credentials: LoginRequest): UserDto

    @Serializable
    data class LoginRequest(
        val username: String,
        val password: String
    )
}