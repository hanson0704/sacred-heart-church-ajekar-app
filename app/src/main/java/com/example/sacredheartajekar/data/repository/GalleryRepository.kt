package com.example.sacredheartajekar.data.repository

import android.net.Uri
import com.example.sacredheartajekar.model.GalleryItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage

class GalleryRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val galleryRef = firestore.collection("gallery")

    fun listenToGallery(onChange: (List<GalleryItem>) -> Unit) {

        galleryRef
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->

                // ✅ HANDLE ERROR
                if (error != null) {
                    onChange(emptyList())
                    return@addSnapshotListener
                }

                val list = snapshot?.documents?.mapNotNull { doc ->

                    val imagesRaw = doc.get("images") as? List<*>

                    val images = imagesRaw
                        ?.mapNotNull { it?.toString() }
                        ?: emptyList()

                    GalleryItem(
                        id = doc.id,
                        title = doc.getString("title") ?: "",
                        description = doc.getString("description") ?: "",
                        images = images,
                        timestamp = doc.getLong("timestamp") ?: 0L
                    )

                } ?: emptyList()

                onChange(list)
            }
    }

    fun uploadGallery(
        uris: List<Uri>,
        title: String,
        description: String,
        onResult: (Boolean) -> Unit
    ) {

        if (uris.isEmpty()) {
            onResult(false)
            return
        }

        val urls = mutableListOf<String>()
        var uploadedCount = 0

        uris.forEach { uri ->

            val ref = storage.reference.child(
                "gallery/${System.currentTimeMillis()}_${uri.lastPathSegment}.jpg"
            )

            ref.putFile(uri)
                .continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let { throw it }
                    }
                    ref.downloadUrl
                }
                .addOnSuccessListener { url ->

                    urls.add(url.toString())
                    uploadedCount++

                    if (uploadedCount == uris.size) {

                        val data = mapOf(
                            "title" to title,
                            "description" to description,
                            "images" to urls,
                            "timestamp" to System.currentTimeMillis()
                        )

                        galleryRef.add(data)
                            .addOnSuccessListener { onResult(true) }
                            .addOnFailureListener { onResult(false) }
                    }
                }
                .addOnFailureListener {
                    onResult(false)
                }
        }
    }

    fun deleteGallery(id: String) {
        galleryRef.document(id).delete()
    }
}