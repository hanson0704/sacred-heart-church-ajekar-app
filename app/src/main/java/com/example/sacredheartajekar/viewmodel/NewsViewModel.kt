package com.example.sacredheartajekar.viewmodel

import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sacredheartajekar.data.repository.NewsRepository
import com.example.sacredheartajekar.model.NewsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date
import java.util.Locale

class NewsViewModel : ViewModel() {

    private val repository = NewsRepository()

    private val _announcements = MutableStateFlow<List<NewsItem>>(emptyList())
    val announcements: StateFlow<List<NewsItem>> = _announcements

    init {
        repository.listenToNews { data ->
            _announcements.value = data
        }
    }

    var isPosting by mutableStateOf(false)
        private set

    var postMessage by mutableStateOf<String?>(null)
        private set


    // POST NEWS
    fun postNews(title: String, type: String) {

        isPosting = true
        postMessage = null

        val currentTime = System.currentTimeMillis()

        val news = NewsItem(
            title = title,
            date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()),
            type = type,
            timestamp = currentTime,
            expiresAt = currentTime + (48 * 60 * 60 * 1000)
        )

        repository.addNews(news) { success ->

            isPosting = false
            postMessage =
                if (success) "Posted successfully"
                else "Failed to post"
        }
    }


    // DELETE NEWS
    fun deleteNews(id: String) {

        repository.deleteNews(id) { success ->

            if (!success) {
                postMessage = "Delete failed"
            }
        }
    }


    // UPDATE NEWS
    fun updateNews(newsItem: NewsItem) {

        repository.updateNews(newsItem) { success ->

            if (!success) {
                postMessage = "Update failed"
            }
        }
    }
}