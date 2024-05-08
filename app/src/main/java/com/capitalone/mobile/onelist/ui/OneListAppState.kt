package com.capitalone.mobile.onelist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberOneListAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): OneListAppState {
    return remember(navController, coroutineScope) {
        OneListAppState(navController)
    }
}

@Stable
class OneListAppState(
    val navController: NavHostController,
) {

    fun onBackClick() {
        navController.popBackStack()
    }
}
