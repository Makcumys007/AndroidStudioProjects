package com.example.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        homeScreenNavGraph(
            newsFeedScreenContent,
            commentsScreenContent
        )
        composable(Screen.Favorite.route) {
            favoriteScreenContent()
        }
        composable(Screen.Profile.route) {
            profileScreenContent()
        }
    }
}