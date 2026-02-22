package com.example.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.domain.PostComment
import com.example.vknewsclient.ui.theme.CommentsScreenState

/**
 * ViewModel для управления состоянием экрана комментариев.
 */
class CommentsViewModel(feedPost: FeedPost): ViewModel() {

    // Внутреннее состояние экрана комментариев
    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    // Публичное состояние для наблюдения
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        // При создании ViewModel сразу загружаем комментарии для переданного поста
        loadComments(feedPost)
    }

    /**
     * Имитация загрузки комментариев.
     */
    private fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        // Обновляем состояние экрана списком комментариев
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments
        )
    }
}
