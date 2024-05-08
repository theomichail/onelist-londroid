package com.capitalone.mobile.onelist.domain.todo

import com.capitalone.mobile.onelist.core.model.data.todo.Todo
import com.capitalone.mobile.onelist.data.todo.api.TodoRepository
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val todoRepository: TodoRepository,
) {
    suspend operator fun invoke(): List<Todo> {
        val todos = todoRepository.fetchTodos()
        return todos.data
    }
}

class AddTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo): Todo {
        val addedTodo = todoRepository.addTodo(todo)
        return addedTodo.data
    }
}

class UpdateTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo): Todo {
        val updatedTodo = todoRepository.updateTodo(todo)
        return updatedTodo.data
    }
}

class DeleteTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo): Boolean {
        val deletedTodo = todoRepository.deleteTodo(todo)
        return deletedTodo.data
    }
}