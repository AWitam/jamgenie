package io.jamgenie

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.jamgenie.ui.home.HomeScreen
import io.jamgenie.ui.library.LibraryScreen

enum class Routes(@StringRes val route: Int) {
    HOME(R.string.nav_home), LIBRARY(R.string.nav_library)
}

@Composable
fun RootNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HOME.name) {
        composable(Routes.HOME.name) {
            HomeScreen(navController)
        }
        composable(Routes.LIBRARY.name) {
            LibraryScreen(
                navController
            )
        }
    }
}