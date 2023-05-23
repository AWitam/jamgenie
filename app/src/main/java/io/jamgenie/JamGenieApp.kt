package io.jamgenie

/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.jamgenie.ui.theme.JamGenieTheme
import java.util.Locale


enum class Screen(val route: String) {
    HOME("home"),
    LIBRARY("library")
}

enum class Tabs(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String,
) {
    HOME(R.string.nav_home, Icons.Rounded.Home, Screen.HOME.name),
    LIBRARY(R.string.nav_library, Icons.Rounded.List, Screen.LIBRARY.name),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JamGenieApp() {
    JamGenieTheme {
        val tabs = remember { Tabs.values() }
        val navController = rememberNavController()

        Scaffold(
            bottomBar = { BottomBar(navController = navController, tabs) }
        ) { innerPaddingModifier ->
            Navigation(
                navController = navController,
                modifier = Modifier.padding(innerPaddingModifier)
            )
        }
    }
}


@Composable
fun BottomBar(navController: NavController, tabs: Array<Tabs>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: Tabs.HOME.route

    val routes = remember { Tabs.values().map { it.route } }
    if (currentRoute in routes) {
        NavigationBar(
        ) {
            tabs.forEach { tab ->
                NavigationBarItem(
                    label = { Text(stringResource(tab.title).uppercase(Locale.getDefault())) },
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