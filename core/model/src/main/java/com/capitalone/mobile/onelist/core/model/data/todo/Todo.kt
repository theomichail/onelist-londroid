package com.capitalone.mobile.onelist.core.model.data.todo

import java.time.LocalDate

data class Todo(
    val id: Int = 0,
    val text: String,
    val isCompleted: Boolean = false,
    val dateCreated: LocalDate = LocalDate.now(),
)
