package com.example.sacredheartajekar.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sacredheartajekar.model.MassInfo
import com.google.firebase.firestore.FirebaseFirestore

class MassViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    // 🔥 State
    val masses = mutableStateOf(emptyList<MassInfo>())

    init {
        listenToMass()
    }

    // 🔥 LISTEN REAL-TIME FROM FIRESTORE
    private fun listenToMass() {
        db.collection("mass")
            .addSnapshotListener { snapshot, error ->

                // 🔴 HANDLE ERROR (VERY IMPORTANT)
                if (error != null) {
                    println("Firestore error: ${error.message}")
                    return@addSnapshotListener
                }

                val list = snapshot?.documents?.map { doc ->
                    MassInfo(
                        id = doc.id,
                        title = doc.getString("title") ?: "",
                        time = doc.getString("time") ?: "",
                        note = doc.getString("note") ?: ""
                    )
                } ?: emptyList()

                masses.value = list
            }
    }

    // 🔥 ADD NEW MASS
    fun addMass(mass: MassInfo) {
        db.collection("mass")
            .add(
                mapOf(
                    "title" to mass.title,
                    "time" to mass.time,
                    "note" to mass.note
                )
            )
    }

    // 🔥 UPDATE EXISTING MASS
    fun updateMass(mass: MassInfo) {
        db.collection("mass")
            .document(mass.id)
            .set(
                mapOf(
                    "title" to mass.title,
                    "time" to mass.time,
                    "note" to mass.note
                )
            )
    }

    // 🔥 DELETE SINGLE MASS
    fun deleteMass(id: String) {
        db.collection("mass")
            .document(id)
            .delete()
    }

    // 🔥 CLEAR ALL (RESET TO DEFAULT)
    fun clearMass() {
        db.collection("mass")
            .get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.forEach { doc ->
                    doc.reference.delete()
                }
            }
    }
}