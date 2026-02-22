package com.example.vknewsclient.ui.theme

import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.domain.PostComment

/**
 * Описание состояний экрана комментариев.
 */
sealed class CommentsScreenState {

    // Начальное состояние (загрузка)
    object Initial: CommentsScreenState()

    // Состояние с отображением контента: пост и список комментариев к нему
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ): CommentsScreenState()
}
