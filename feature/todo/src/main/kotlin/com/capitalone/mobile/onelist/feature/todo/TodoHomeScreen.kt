package com.capitalone.mobile.onelist.feature.todo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.capitalone.mobile.onelist.core.ui.ObserveAsEvents

@Composable
fun TodoHomeScreen(
    navigateToStandard: () -> Unit,
    navigateToSpecial: () -> Unit,
    viewModel: TodoHomeViewModel = hiltViewModel()
) {
    ObserveAsEvents(viewModel.showSpecialUi) {
        if (it) {
            navigateToSpecial()
        } else {
            navigateToStandard()
        }
    }
}