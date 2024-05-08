package com.capitalone.mobile.onelist.data.user.impl.dto

import com.capitalone.mobile.onelist.core.model.data.user.User
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UserDto(
    val id: Int,
    val username: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
) {
    fun toUser() = User(
        id = id,
        username = username,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = LocalDate.parse(dateOfBirth)
    )
}