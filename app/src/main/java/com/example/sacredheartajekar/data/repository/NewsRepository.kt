package com.example.sacredheartajekar.data.repository

import com.example.sacredheartajekar.model.NewsItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NewsRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val newsRef = firestore.collection("news")

    // 🔥 REALTIME LISTENER
    fun listenToNews(onChange: (List<NewsItem>) -> Unit) {
        newsRef
            .orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, _ ->
                val newsList = snapshot?.toObjects(NewsItem::class.java) ?: emptyList()
                onChange(newsList)
            }
    }

    // POST news (same as before)
    fun addNews(newsItem: NewsItem, onResult: (Boolean) -> Unit) {
        newsRef
            .add(newsItem)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
}