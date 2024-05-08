package com.capitalone.mobile.onelist.data.todo.impl

import com.capitalone.mobile.onelist.data.todo.impl.dto.TodoDto
import retrofit2.http.GET

interface TodoApi {

    @GET("/todos")
    suspend fun getTodos(): List<TodoDto>
}