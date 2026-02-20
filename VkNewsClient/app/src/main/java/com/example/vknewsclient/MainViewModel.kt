package com.example.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.domain.PostComment
import com.example.vknewsclient.domain.StatisticItem
import com.example.vknewsclient.ui.theme.HomeScreenState
import com.example.vknewsclient.ui.theme.NavigationItem

class MainViewModel: ViewModel() {
    private val comments = mutableListOf<PostComment>().apply {
        repeat(5) {
            add(PostComment(id = it))
        }
    }
    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(3) {
            add(FeedPost(id = it))
        }
    }
    private val initialState = HomeScreenState.Posts(sourceList)
    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private var savedState: HomeScreenState? = initialState

    private val _selectedNavItem = MutableLiveData<NavigationItem>(NavigationItem.Home)
    val selectedNavItem: LiveData<NavigationItem> = _selectedNavItem

    fun selectNavItem(item: NavigationItem) {
        _selectedNavItem.value = item
    }

    fun showComments(feedPost: FeedPost) {
        savedState = _screenState.value
        _screenState.value = HomeScreenState.Comments(feedPost, comments)

    }

    fun closeComments() {
        _screenState.value = savedState
    }

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) {
            return
        }
        val oldPosts = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldsItem: StatisticItem ->
                if(oldsItem.type == item.type) {
                    oldsItem.copy(count = oldsItem.count + 1)
                } else {
                    oldsItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = HomeScreenState.Posts(newPosts)
    }

    fun remove(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) {
            return
        }
        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = HomeScreenState.Posts(oldPosts)
    }
}