package com.capitalone.mobile.onelist.feature.todo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.capitalone.mobile.onelist.feature.todo.TodoHomeScreen
import com.capitalone.mobile.onelist.feature.todo.special.SpecialScreen
import com.capitalone.mobile.onelist.feature.todo.standard.StandardScreen

const val todoNavigationRoute = "todo_route"
private const val standardRoute = "standard_route"
private const val specialRoute = "special_route"

fun NavGraphBuilder.todoFeature(navController: NavController) {
    composable(route = todoNavigationRoute) {
        TodoHomeScreen(
            navigateToStandard = { navController.navigate(standardRoute) { popUpTo(0) } },
            navigateToSpecial = { navController.navigate(specialRoute) { popUpTo(0) } }
        )
    }
    composable(route = standardRoute) {
        StandardScreen()
    }
    composable(route = specialRoute) {
        SpecialScreen()
    }
}