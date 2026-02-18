package com.example.instagram

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.instagram.ui.theme.InstagramProfileCard
import com.example.instagram.ui.theme.InstagramTheme
import com.example.instagram.ui.theme.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            /*InstagramTheme() {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)) {
                    InstagramProfileCard(viewModel = viewModel)
                }
            }*/
            Test(viewModel)
        }
    }
}





@Composable
fun Test(viewModel: MainViewModel) {
    InstagramTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn() {
                item{
                    Text(text ="Title", color = Color.White)
                }
                items(10) {
                    Log.d("LAZY_LIST_TEST", "Number: {$it}")
                    InstagramProfileCard(viewModel = viewModel)
                }
                item {
                    Image(painter = painterResource(id = R.drawable.ic_instagram_55), contentDescription = null)
                }
                items(500) {
                    InstagramProfileCard(viewModel = viewModel)
                }
            }
        }
    }
}