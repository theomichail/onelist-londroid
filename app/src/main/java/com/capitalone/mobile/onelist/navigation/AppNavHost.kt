package com.capitalone.mobile.onelist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.capitalone.mobile.onelist.feature.todo.navigation.todoNavigationRoute
import com.capitalone.mobile.onelist.feature.todo.navigation.todoFeature
import com.capitalone.mobile.onelist.feature.login.navigation.loginNavigationRoute
import com.capitalone.mobile.onelist.feature.login.navigation.loginScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = loginNavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        loginScreen { navController.navigate(todoNavigationRoute) {
            popUpTo(0)
        } }
        todoFeature(navController)
    }
}
