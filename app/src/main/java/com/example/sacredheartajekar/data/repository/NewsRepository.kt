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
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, _ ->

                val newsList = snapshot?.documents?.map { doc ->

                    NewsItem(
                        id = doc.id,
                        title = doc.getString("title") ?: "",
                        date = doc.getString("date") ?: "",
                        type = doc.getString("type") ?: "",
                        timestamp = doc.getLong("timestamp") ?: 0L,
                        expiresAt = doc.getLong("expiresAt") ?: 0L
                    )

                } ?: emptyList()

                onChange(newsList)
            }
    }

    // ➕ ADD NEWS
    fun addNews(newsItem: NewsItem, onResult: (Boolean) -> Unit) {

        newsRef
            .add(newsItem)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    // 🗑 DELETE NEWS
    fun deleteNews(id: String, onResult: (Boolean) -> Unit) {

        firestore.collection("news")
            .document(id)
            .delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun updateNews(newsItem: NewsItem, onResult: (Boolean) -> Unit) {

        newsRef
            .document(newsItem.id)
            .set(newsItem)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
}