package com.example.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vknewsclient.ui.theme.MainScreen
import com.example.vknewsclient.ui.theme.VkNewsClientTheme

/**
 * Главная Activity приложения.
 * Является точкой входа и устанавливает основной контент с использованием Jetpack Compose.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Оборачиваем всё приложение в пользовательскую тему VkNewsClientTheme
            VkNewsClientTheme {
                // Вызываем главный экран приложения
                MainScreen()
            }
        }
    }
}
