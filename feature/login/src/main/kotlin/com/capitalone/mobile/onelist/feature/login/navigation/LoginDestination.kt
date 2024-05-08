package com.capitalone.mobile.onelist.feature.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.capitalone.mobile.onelist.feature.login.LoginScreen

const val loginNavigationRoute = "login_route"

fun NavGraphBuilder.loginScreen(
    navigateToHome: () -> Unit
) {
    composable(route = loginNavigationRoute) {
        LoginScreen(navigateToHome)
    }
}