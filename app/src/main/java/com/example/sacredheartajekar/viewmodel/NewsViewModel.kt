package com.example.sacredheartajekar.viewmodel

import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.getValue
import java.util.Locale
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.autofill.ContentDataType.Companion.Date
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sacredheartajekar.data.repository.NewsRepository
import com.example.sacredheartajekar.model.NewsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

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


    fun postNews(title: String, type: String) {
        isPosting = true
        postMessage = null

        val news = NewsItem(
            title = title,
            date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()),
            type = type
        )

        repository.addNews(news) { success ->
            isPosting = false
            postMessage = if (success) "Posted successfully" else "Failed to post"
        }
    }
}
