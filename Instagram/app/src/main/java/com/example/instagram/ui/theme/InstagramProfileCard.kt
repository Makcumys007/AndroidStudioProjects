package com.example.instagram.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagram.InstagramModel
import com.example.instagram.MainViewModel
import com.example.instagram.R


@Composable
fun InstagramProfileCard(
    model: InstagramModel,
    onFollowedButtonClickListener: (InstagramModel) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        Column(Modifier.padding(16.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = CircleShape),
                        painter = painterResource(id = R.drawable.ic_instagram_55),
                        contentDescription = "Logo",
                        contentScale = ContentScale.None
                    )
                    UserStatistic("Posts", value = "6,950")
                    UserStatistic("Followers", value = "436")
                    UserStatistic("Following", value = "76")
                }
                Text(
                    "Instagram ${model.id}",
                    fontSize = 32.sp,
                    fontFamily = FontFamily.Cursive,
                )
                Text(
                    "#${model.title}",
                    fontSize = 14.sp,
                )
                Text(
                    "www.facebook.com/emotional_health",
                    fontSize = 14.sp,
                )
            FollowButton(isFollowed = model.isFollowed) {
                onFollowedButtonClickListener(model)
            }
        }
    }
}

@Composable
private fun FollowButton(
    isFollowed: Boolean,
    clickListener: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFollowed) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colorScheme.primary
            }
        ),
        onClick = { clickListener() },
    ) {
        val text = if (isFollowed) {
            "Unfollow"
        } else {
            "Follow"
        }
        Text(text)
    }
}

@Composable
private fun UserStatistic (
    title: String,
    value: String
) {
    Column(modifier = Modifier
        .height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            value,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
            textDecoration = TextDecoration.Underline
        )
        Text(
            title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/*
@Preview
@Composable
fun PreviewCardLight(){
    InstagramTheme(darkTheme = false) {
        InstagramProfileCard()
    }
}

@Preview
@Composable
fun PreviewCardDark(){
    InstagramTheme(darkTheme = true) {
        InstagramProfileCard()
    }
}
*/
