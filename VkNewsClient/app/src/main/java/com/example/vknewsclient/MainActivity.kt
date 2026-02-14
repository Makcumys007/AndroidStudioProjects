package com.example.vknewsclient

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Обязательно оборачиваем контент в твою тему
          /*  VkNewsClientTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp)) {
                    PostCard()
                }
            }*/
            Test()
        }
    }
}


@Composable
private fun Test() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Example3()
    }
}

@Composable
private fun Example1() {
    OutlinedButton (onClick = {}) {
        Text("Hello World")
    }
}

@Composable
private fun Example2() {
    var textValue by remember { mutableStateOf("") }

    TextField(
        value = textValue,
        onValueChange = { textValue = it },
        label = { Text("Label") },
        singleLine = false
    )
}

@Composable
private fun Example3() {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                openDialog.value = false
            },
            title = { Text(text = "Are you sure?") },
            text = {
                Text(
                    "Do you want to delete this file?"
                )
            },
            confirmButton = {
                TextButton(onClick = { openDialog.value = false }) { Text("Yes") }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false }) { Text("No") }
            },
        )
    }
}