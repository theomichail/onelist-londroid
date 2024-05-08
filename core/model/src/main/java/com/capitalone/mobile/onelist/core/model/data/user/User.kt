package com.capitalone.mobile.onelist.core.model.data.user

import java.time.LocalDate

data class User(
    val id: Int = 0,
    val username: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val dateOfBirth: LocalDate = LocalDate.now(),
)
