package com.capitalone.mobile.onelist.data.todo.impl

import com.capitalone.mobile.onelist.core.model.data.todo.Todo
import com.capitalone.mobile.onelist.core.network.NetworkResponse
import com.capitalone.mobile.onelist.data.todo.api.TodoRepository
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkTodoRepository @Inject constructor(
    retrofit: Retrofit
) : TodoRepository {
    private val todoApi = retrofit.create(TodoApi::class.java)

    override suspend fun fetchTodos(): NetworkResponse<List<Todo>> {
        return try {
            val todosDto = todoApi.getTodos()

            NetworkResponse(todosDto.map { it.toTodo() })
        } catch (e: Exception) {
            // TODO: Handle gracefully
            throw e
        }
    }

    override suspend fun addTodo(todo: Todo): NetworkResponse<Todo> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTodo(todo: Todo): NetworkResponse<Todo> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTodo(todo: Todo): NetworkResponse<Boolean> {
        TODO("Not yet implemented")
    }
}