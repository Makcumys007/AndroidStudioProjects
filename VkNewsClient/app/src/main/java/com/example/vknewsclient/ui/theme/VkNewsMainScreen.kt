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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vknewsclient.NewsFeedViewModel
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.navigation.AppNavGraph
import com.example.vknewsclient.navigation.rememberNavigationState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    // Управление состоянием навигации (кастомный помощник для работы с NavHostController)
    val navigationState = rememberNavigationState()

    // Состояние для хранения поста, к которому мы хотим посмотреть комментарии.
    // Если null — показываем ленту, если не null — экран комментариев для конкретного поста.
    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(
        // Настройка нижней панели навигации
        bottomBar = {
            NavigationBar {
                // Получаем текущий маршрут (route) из backstack для подсветки активной иконки
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Проходим циклом по элементам навигации (Главная, Избранное, Профиль)
                AppNavigation.items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            // Переход на выбранный экран при клике
                            navigationState.navigateTo(item.screen.route)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        icon = {
                            Icon(
                                // Выбираем иконку (закрашенная или контурная) в зависимости от активности вкладки
                                imageVector = if (currentRoute == item.screen.route) item.selectedIcon else item.unselectedIcon,
                                contentDescription = stringResource(id = item.titleResId)
                            )
                        },
                    )
                }
            }
        },
    ) { paddingValues ->
        // Настройка графа навигации, который определяет, какой контент отрисовывать
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {
                // Логика переключения между лентой новостей и комментариями внутри вкладки "Главная"
                if (commentsToPost.value == null) {
                    HomeScreen(
                        paddingValues = paddingValues,
                        onCommentClickListener = {
                            // При клике на иконку комментариев в ленте, сохраняем пост в состояние
                            commentsToPost.value = it
                        }
                    )
                } else {
                    CommentsScreen(
                        onBackPressed = {
                            // При нажатии "назад" сбрасываем состояние в null, чтобы вернуться к ленте
                            commentsToPost.value = null
                        },
                        feedPost = commentsToPost.value!!
                    )
                }
            },
            // Заглушки для разделов "Избранное" и "Профиль"
            favoriteScreenContent = { TextCounter("Favorites") },
            profileScreenContent = { TextCounter("Profile") }
        )
    }
}

/**
 * Вспомогательный компонент для демонстрации работы с состоянием.
 * Отображает название экрана и количество кликов по тексту.
 * rememberSaveable позволяет сохранить значение счетчика при повороте экрана.
 */
@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Blue
    )
}
