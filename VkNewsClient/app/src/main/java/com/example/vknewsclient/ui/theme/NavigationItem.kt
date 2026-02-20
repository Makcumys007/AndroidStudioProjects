package com.example.vknewsclient.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.vknewsclient.R
import com.example.vknewsclient.navigation.Screen

sealed class NavigationItem(
    val titleResId: Int, // Используем Int для ID ресурса
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
    val badgeCount: Int? = null,
    val screen: Screen
) {
    object Home : NavigationItem(
        titleResId = R.string.navigation_item_main,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = "home",
        screen = Screen.NewsFeed
    )
    object Favorites : NavigationItem(
        titleResId = R.string.navigation_item_favorites,
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        route = "favorites",
        screen = Screen.Favorite,
        badgeCount = 5
    )
    object Profile : NavigationItem(
        titleResId = R.string.navigation_item_profile,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        screen = Screen.Profile,
        route = "profile"
    )
}

// Выносим список в объект, чтобы к нему был удобный доступ
object AppNavigation {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Favorites,
        NavigationItem.Profile
    )
}