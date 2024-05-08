package com.capitalone.mobile.onelist.data.user.api

import com.capitalone.mobile.onelist.core.model.data.user.User
import com.capitalone.mobile.onelist.core.network.NetworkResponse

interface UserRepository {
    suspend fun fetchUser(
        username: String,
        password: String,
    ) : NetworkResponse<Boolean>

    fun getUser() : User?
}