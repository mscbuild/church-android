package com.church.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.church.app.screens.BibleScreen
import com.church.app.screens.CalendarScreen
import com.church.app.screens.HomeScreen
import com.church.app.screens.MusicPlayerScreen

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home", "Главная", Icons.Default.Home)
    object Bible : BottomNavItem("bible", "Библия", Icons.Default.Book)
    object Music : BottomNavItem("music", "Музыка", Icons.Default.MusicNote)
    object Calendar : BottomNavItem("calendar", "Календарь", Icons.Default.CalendarMonth)
}

@Composable
fun ChurchNavigation(navController: androidx.navigation.NavController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                val items = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Bible,
                    BottomNavItem.Music,
                    BottomNavItem.Calendar
                )
                
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen()
            }
            composable(BottomNavItem.Bible.route) {
                BibleScreen()
            }
            composable(BottomNavItem.Music.route) {
                MusicPlayerScreen()
            }
            composable(BottomNavItem.Calendar.route) {
                CalendarScreen()
            }
        }
    }
}