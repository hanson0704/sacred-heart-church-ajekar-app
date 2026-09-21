package com.example.sacredheartajekar.model

data class GalleryItem(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val images: List<String> = emptyList(),   // ✅ MATCHED
    val timestamp: Long = 0L
)