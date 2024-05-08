package com.capitalone.mobile.onelist.feature.todo.standard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capitalone.mobile.onelist.core.model.data.todo.Todo
import com.capitalone.mobile.onelist.domain.todo.GetTodosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandardViewModel @Inject constructor(
    private val getTodos: GetTodosUseCase
) : ViewModel() {
    private data class ViewModelState(
        val todos: List<Todo> = emptyList(),
        val isLoading: Boolean = true,
    ) {
        fun toUiState(): UiState {
            return when {
                isLoading -> UiState.Loading
                else -> UiState.Loaded(todos)
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Loaded(val todos: List<Todo>) : UiState()
    }

    private val viewModelState = MutableStateFlow(ViewModelState())

    val uiState = viewModelState
        .map(ViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            viewModelState.value.toUiState()
        )

    init {
        viewModelScope.launch {
            val todos = getTodos()
            viewModelState.update { it.copy(todos = todos, isLoading = false) }
        }
    }
}