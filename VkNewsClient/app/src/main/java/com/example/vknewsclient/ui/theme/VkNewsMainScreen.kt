package com.example.vknewsclient.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vknewsclient.MainViewModel
import com.example.vknewsclient.navigation.AppNavGraph
import com.example.vknewsclient.navigation.NavigationState
import com.example.vknewsclient.navigation.Screen
import com.example.vknewsclient.navigation.rememberNavigationState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navigationState = rememberNavigationState()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                AppNavigation.items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            navigationState.navigateTo(item.screen.route)
                      },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        icon = {
                            Icon(
                                imageVector = if (currentRoute == item.screen.route) item.selectedIcon else item.unselectedIcon,
                                contentDescription = stringResource(id = item.titleResId)
                            )
                        },
                    )
                }
            }
        },
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {HomeScreen(viewModel = viewModel, paddingValues = paddingValues) },
            favoriteScreenContent = {TextCounter("Favorites") },
            profileScreenContent = {TextCounter("Profile") }
        )
    }
}

@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Text(modifier = Modifier.clickable { count++ }, text = "$name Count: $count", color = Color.Blue)
}
