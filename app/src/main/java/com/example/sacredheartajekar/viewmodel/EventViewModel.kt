package com.example.sacredheartajekar.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sacredheartajekar.data.repository.EventRepository
import com.example.sacredheartajekar.model.EventItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EventViewModel : ViewModel() {

    private val repository = EventRepository()

    private val _events = MutableStateFlow<List<EventItem>>(emptyList())
    val events: StateFlow<List<EventItem>> = _events

    var isPosting by mutableStateOf(false)
        private set

    var message by mutableStateOf<String?>(null)
        private set

    init {
        repository.listenToEvents {
            _events.value = it
        }
    }

    fun addEvent(title: String, description: String, date: String) {

        if (title.isBlank() || description.isBlank() || date.isBlank()) {
            message = "All fields required"
            return
        }

        isPosting = true

        val event = EventItem(
            title = title.trim(),
            description = description.trim(),
            date = date.trim(),
            timestamp = System.currentTimeMillis()
        )

        repository.addEvent(event) { success ->
            isPosting = false
            message = if (success) "Event added" else "Failed"
        }
    }

    fun deleteEvent(id: String) {
        repository.deleteEvent(id) { success ->
            message = if (success) "Deleted" else "Delete failed"
        }
    }

    fun updateEvent(event: EventItem) {
        repository.updateEvent(event) { success ->
            message = if (success) "Updated" else "Update failed"
        }
    }
}