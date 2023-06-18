package io.jamgenie

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import io.jamgenie.ui.home.HomeScreen
import io.jamgenie.ui.home.HomeViewModel
import io.jamgenie.ui.library.LibraryScreen
import io.jamgenie.ui.library.item.LibraryItemScreen
import io.jamgenie.ui.library.item.LibraryItemViewModel
import io.jamgenie.ui.practice.PracticeScreen
import io.jamgenie.ui.practice.PracticeViewModel

object Routes {
    const val HOME = "home"
    const val LIBRARY = "library"
    const val LIBRARY_ITEM = "library/{itemId}"
    const val PRACTICE = "practice"
    const val PRACTICE_ITEM = "practice/{itemId}"
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RootNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(route = Routes.HOME) {
            HomeScreen(navController)
        }
        composable(route = Routes.LIBRARY) {
            LibraryScreen(
                navController
            )
        }
        composable(
            route = Routes.LIBRARY_ITEM, arguments = listOf(navArgument("itemId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val libraryItemViewModel: LibraryItemViewModel = viewModel(
                factory = LibraryItemViewModel.provideFactory(
                    owner = backStackEntry, defaultArgs = backStackEntry.arguments
                )
            )
            LibraryItemScreen(libraryItemViewModel,
                onBackPress = { navController.popBackStack() },
                navigateToPractice = { itemId -> navController.navigate("${Routes.PRACTICE}/${itemId}") })
        }
        composable(
            route = Routes.PRACTICE_ITEM, arguments = listOf(navArgument("itemId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val practiceViewModel: PracticeViewModel = viewModel(
                factory = PracticeViewModel.provideFactory(
                    owner = backStackEntry, defaultArgs = backStackEntry.arguments
                )
            )
            PracticeScreen(
                viewModel = practiceViewModel,
                onBackPress = { navController.popBackStack() },
                onFinishedRoutine = { navController.navigate(Routes.HOME) }
            )

        }
    }
}


