package com.capitalone.mobile.onelist.data.todo.impl.dto

import com.capitalone.mobile.onelist.core.model.data.todo.Todo
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class TodoDto(
    val id: Int,
    val text: String,
    val isCompleted: Boolean,
    val dateCreated: String,
) {
    fun toTodo() = Todo(
        id = id,
        text = text,
        isCompleted = isCompleted,
        dateCreated = LocalDate.parse(dateCreated)
    )
}
