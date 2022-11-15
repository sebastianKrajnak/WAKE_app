package com.example.wake_app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }
}
@Preview
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Statistics,
        BottomBarScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach {
            screen -> AddItem(
                screen = screen,
                currentDest = currentDestination,
                navController = navController
            )
        }
    }
}

@Preview
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDest: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.tittle) },
        icon = { Icon(imageVector = screen.icon, contentDescription = "Navigation Icon")},
        selected = currentDest?.hierarchy?.any { it.route == screen.route } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = { navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        } }
    )
}