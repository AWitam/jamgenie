package io.jamgenie

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import io.jamgenie.ui.home.HomeScreen
import io.jamgenie.ui.library.LibraryScreen
import io.jamgenie.ui.library.item.LibraryItemScreen
import io.jamgenie.ui.library.item.LibraryItemViewModel

enum class Routes(@StringRes val route: Int) {
    HOME(R.string.nav_home), LIBRARY(R.string.nav_library), LIBRARY_ITEM(R.string.nav_library_item),

}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
        composable(
            "library/{itemId}", arguments = listOf(navArgument("itemId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val libraryItemViewModel: LibraryItemViewModel = viewModel(
                factory = LibraryItemViewModel.provideFactory(
                    owner = backStackEntry,
                    defaultArgs = backStackEntry.arguments
                )
            )
            LibraryItemScreen(
                libraryItemViewModel,
                onBackPress = { navController.popBackStack() }
            )
        }

    }
}
