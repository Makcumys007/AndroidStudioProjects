package com.example.vknewsclient.domain

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vknewsclient.R
import com.example.vknewsclient.domain.StatisticItem

data class FeedPost (
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, count = 699),
        StatisticItem(type = StatisticType.SHARE, count = 7),
        StatisticItem(type = StatisticType.COMMENTS, count = 6),
        StatisticItem(type = StatisticType.LIKES, count = 24)
    )
)