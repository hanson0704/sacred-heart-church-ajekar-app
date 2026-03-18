package com.example.sacredheartajekar.viewmodel

import android.net.Uri
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.sacredheartajekar.data.repository.GalleryRepository
import com.example.sacredheartajekar.model.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GalleryViewModel : ViewModel() {

    private val repository = GalleryRepository()

    private val _images = MutableStateFlow<List<GalleryItem>>(emptyList())
    val images: StateFlow<List<GalleryItem>> = _images

    var isUploading by mutableStateOf(false)
        private set

    var message by mutableStateOf<String?>(null)
        private set

    init {
        repository.listenToGallery {
            _images.value = it
        }
    }

    fun uploadGallery(title: String, description: String, uris: List<Uri>) {

        if (title.isBlank() || uris.isEmpty()) {
            message = "Please add title and images"
            return
        }

        isUploading = true
        message = null

        repository.uploadGallery(uris, title, description) { success ->
            isUploading = false
            message = if (success) "Uploaded successfully" else "Upload failed"
        }
    }

    fun deleteGallery(id: String) {
        repository.deleteGallery(id)
        message = "Deleted"
    }
}