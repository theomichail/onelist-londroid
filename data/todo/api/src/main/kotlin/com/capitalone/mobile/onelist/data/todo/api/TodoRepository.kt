package com.capitalone.mobile.onelist.data.todo.api

import com.capitalone.mobile.onelist.core.model.data.todo.Todo
import com.capitalone.mobile.onelist.core.network.NetworkResponse

interface TodoRepository {
    suspend fun fetchTodos(): NetworkResponse<List<Todo>>
    suspend fun addTodo(todo: Todo): NetworkResponse<Todo>
    suspend fun updateTodo(todo: Todo): NetworkResponse<Todo>
    suspend fun deleteTodo(todo: Todo): NetworkResponse<Boolean>
}