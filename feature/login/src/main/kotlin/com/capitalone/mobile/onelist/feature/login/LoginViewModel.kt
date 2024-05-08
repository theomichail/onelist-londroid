package com.capitalone.mobile.onelist.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capitalone.mobile.onelist.core.ui.send
import com.capitalone.mobile.onelist.domain.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUser: LoginUseCase
) : ViewModel() {

    data class UiState(
        val incorrectCredentials: Boolean = false,
        val isLoading: Boolean = false,
    )

    private val _uiState = MutableStateFlow(UiState())

    val uiState: StateFlow<UiState> = _uiState

    private val _loginSuccessful = Channel<Unit>()
    val loginSuccessful = _loginSuccessful.receiveAsFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val successful = loginUser(username, password)

            if (successful) {
                _loginSuccessful.send()
            } else {
                _uiState.update { it.copy(incorrectCredentials = true, isLoading = false) }
            }
        }
    }
}