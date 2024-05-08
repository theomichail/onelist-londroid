package com.capitalone.mobile.onelist.data.todo.fakeimpl

import com.capitalone.mobile.onelist.core.model.data.todo.Todo
import com.capitalone.mobile.onelist.core.network.NetworkResponse
import com.capitalone.mobile.onelist.data.todo.api.TodoRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class FakeTodoRepository @Inject constructor() : TodoRepository {
    override suspend fun fetchTodos(): NetworkResponse<List<Todo>> {
        delay(300)
        return NetworkResponse(todos)
    }

    override suspend fun addTodo(todo: Todo): NetworkResponse<Todo> {
        delay(300)
        return NetworkResponse(todo.copy(
            id = Random.Default.nextInt()
        ))
    }

    override suspend fun updateTodo(todo: Todo): NetworkResponse<Todo> {
        delay(300)
        return NetworkResponse(todo)
    }

    override suspend fun deleteTodo(todo: Todo): NetworkResponse<Boolean> {
        delay(300)
        return NetworkResponse(true)
    }
}

private val todos = listOf(
    Todo(
        id = 1,
        text = "Buy milk",
        isCompleted = true
    ),
    Todo(
        id = 2,
        text = "Call mum",
        isCompleted = false
    ),
    Todo(
        id = 3,
        text = "Go to the gym",
        isCompleted = false
    ),
    Todo(
        id = 4,
        text = "Read a book",
        isCompleted = false
    ),
    Todo(
        id = 5,
        text = "Clean the house",
        isCompleted = true
    ),
    Todo(
        id = 6,
        text = "Do laundry",
        isCompleted = true
    ),
)