package com.capitalone.mobile.onelist.feature.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capitalone.mobile.onelist.domain.todo.SpeakingAtLondroidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoHomeViewModel @Inject constructor(
    private val isACoolGuy: SpeakingAtLondroidUseCase
): ViewModel() {
    private val _showSpecialUi = Channel<Boolean>()
    val showSpecialUi = _showSpecialUi.receiveAsFlow()

    init {
        viewModelScope.launch {
            _showSpecialUi.send(isACoolGuy())
        }
    }
}