package com.example.sacredheartajekar.data.repository

import com.example.sacredheartajekar.model.EventItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class EventRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val eventsRef = firestore.collection("events")

    // 🔥 Realtime listener
    fun listenToEvents(onChange: (List<EventItem>) -> Unit) {

        eventsRef
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, _ ->

                val list = snapshot?.documents?.map { doc ->
                    EventItem(
                        id = doc.id,
                        title = doc.getString("title") ?: "",
                        description = doc.getString("description") ?: "",
                        date = doc.getString("date") ?: "",
                        timestamp = doc.getLong("timestamp") ?: 0L
                    )
                } ?: emptyList()

                onChange(list)
            }
    }

    // ➕ Add event
    fun addEvent(event: EventItem, onResult: (Boolean) -> Unit) {
        eventsRef.add(event)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
    fun deleteEvent(id: String, onResult: (Boolean) -> Unit) {
        eventsRef.document(id)
            .delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun updateEvent(event: EventItem, onResult: (Boolean) -> Unit) {
        eventsRef.document(event.id)
            .set(event)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
}