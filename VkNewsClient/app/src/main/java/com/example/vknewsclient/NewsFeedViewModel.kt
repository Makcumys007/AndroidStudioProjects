package com.example.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.domain.StatisticItem
import com.example.vknewsclient.ui.theme.NewsFeedScreenState
import com.example.vknewsclient.ui.theme.NavigationItem

/**
 * ViewModel для управления состоянием ленты новостей.
 */
class NewsFeedViewModel: ViewModel() {

    // Исходный список постов (заглушка)
    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(3) {
            add(FeedPost(id = it))
        }
    }

    // Начальное состояние экрана с постами
    private val initialState = NewsFeedScreenState.Posts(sourceList)

    // Внутреннее Mutable состояние экрана
    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    // Публичное LiveData состояние для наблюдения в Compose
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    // Выбранный элемент в навигации
    private val _selectedNavItem = MutableLiveData<NavigationItem>(NavigationItem.Home)
    val selectedNavItem: LiveData<NavigationItem> = _selectedNavItem

    /**
     * Выбор элемента навигации.
     */
    fun selectNavItem(item: NavigationItem) {
        _selectedNavItem.value = item
    }

    /**
     * Обновление счетчиков статистики (лайки, просмотры и т.д.) для конкретного поста.
     */
    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) {
            return
        }
        val oldPosts = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        // Создаем новый список статистики с обновленным значением
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldsItem: StatisticItem ->
                if(oldsItem.type == item.type) {
                    oldsItem.copy(count = oldsItem.count + 1)
                } else {
                    oldsItem
                }
            }
        }
        // Создаем копию поста с обновленной статистикой
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        // Обновляем список постов
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(newPosts)
    }

    /**
     * Удаление поста из ленты.
     */
    fun remove(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) {
            return
        }
        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(oldPosts)
    }
}
