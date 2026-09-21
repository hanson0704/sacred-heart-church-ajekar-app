package com.example.sacredheartajekar.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sacredheartajekar.model.MassInfo
import com.example.sacredheartajekar.viewmodel.MassViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEditMassScreen(
    onBack: () -> Unit,
    viewModel: MassViewModel = viewModel()
) {

    val massList by viewModel.masses

    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    var editingMass by remember { mutableStateOf<MassInfo?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Mass") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize() // ✅ FIX 1 (prevents layout issues)
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Existing Masses",
                style = MaterialTheme.typography.titleMedium
            )

            // ✅ SAFE EMPTY STATE
            if (massList.isEmpty()) {
                Text(
                    text = "No Mass added. Using default timings.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            massList.forEach { mass ->

                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth() // ✅ FIX 2
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {
                            Text(mass.title, fontWeight = FontWeight.Bold)
                            Text(mass.time)

                            if (mass.note.isNotBlank()) {
                                Text(mass.note)
                            }
                        }

                        Row {

                            TextButton(
                                onClick = {
                                    editingMass = mass
                                    title = mass.title
                                    time = mass.time
                                    note = mass.note
                                }
                            ) {
                                Text("Edit")
                            }

                            TextButton(
                                onClick = {
                                    viewModel.deleteMass(mass.id)
                                }
                            ) {
                                Text(
                                    "Delete",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }

            Divider()

            Text(
                text = if (editingMass == null) "Add New Mass" else "Edit Mass",
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = time,
                onValueChange = { time = it },
                label = { Text("Time") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Note") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {

                    // ✅ VALIDATION (prevents bad data crash)
                    if (title.isBlank() || time.isBlank()) return@Button

                    if (editingMass == null) {

                        viewModel.addMass(
                            MassInfo(
                                id = "",
                                title = title,
                                time = time,
                                note = note
                            )
                        )

                    } else {

                        viewModel.updateMass(
                            editingMass!!.copy(
                                title = title,
                                time = time,
                                note = note
                            )
                        )

                        editingMass = null
                    }

                    // reset fields
                    title = ""
                    time = ""
                    note = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (editingMass == null) "Add Mass" else "Update Mass")
            }

            TextButton(
                onClick = {
                    viewModel.clearMass()
                    onBack()
                }
            ) {
                Text("Reset to Default")
            }
        }
    }
}