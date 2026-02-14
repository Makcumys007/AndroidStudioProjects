package com.example.firstcoposeproject

import android.R
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstcoposeproject.ui.theme.FirstCoposeProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserInfo(
                        name = "Maxim",
                        age = 23,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val a = 5
    val name = "Max"
    Text(text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun UserInfoPreview() {
    UserInfo(
        name = "Maxim",
        age = 23
    )
}
@Composable
fun UserInfo(name: String, age: Int, modifier: Modifier = Modifier) {
    Column {
        for (i in 1..5) {
            Text(
                text = "Hello $name! Your age is $age.",
                modifier = modifier
            )
        }
    }

}