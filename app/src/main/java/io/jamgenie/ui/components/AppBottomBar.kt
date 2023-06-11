package io.jamgenie.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import io.jamgenie.R
import io.jamgenie.Routes


enum class Tabs(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String,
) {
    LIBRARY(R.string.nav_library, Icons.Rounded.List, Routes.LIBRARY),
    HOME(R.string.nav_home, Icons.Rounded.Home, Routes.HOME),
}

@Composable
fun AppBottomBar(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: Tabs.HOME.route

    val routes = remember { Tabs.values().map { it.route } }
    if (currentRoute in routes) {
        NavigationBar(
        ) {
            Tabs.values().forEach { tab ->
                NavigationBarItem(
                    label = { Text(stringResource(tab.title).uppercase()) },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    icon = { Icon(tab.icon, contentDescription = null) }
                )
            }
        }
    }
}