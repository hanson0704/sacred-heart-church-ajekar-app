package com.example.sacredheartajekar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sacredheartajekar.model.EventItem
import com.example.sacredheartajekar.viewmodel.EventViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.sacredheartajekar.ui.theme.SacredHeartAjekarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {

    val viewModel: EventViewModel = viewModel()
    val events by viewModel.events.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Parish Events",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {},
                actions = {}
            )
        }
    ) { padding ->

        if (events.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No events available")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(events) { event ->
                    EventCard(event, viewModel)
                }
            }
        }
    }
}

@Composable
fun EventCard(event: EventItem, viewModel: EventViewModel) {

    val isAdmin =
        com.google.firebase.auth.FirebaseAuth.getInstance().currentUser != null

    var showEditDialog by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(event.title, fontWeight = FontWeight.Bold)

            Text("📅 ${event.date}")

            Spacer(modifier = Modifier.height(6.dp))

            Text(event.description)

            if (isAdmin) {

                Spacer(modifier = Modifier.height(10.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                    Button(onClick = { showEditDialog = true }) {
                        Text("Edit")
                    }

                    Button(onClick = {
                        viewModel.deleteEvent(event.id)
                    }) {
                        Text("Delete")
                    }
                }
            }
        }
    }

    if (showEditDialog) {
        EditEventDialog(
            event = event,
            onDismiss = { showEditDialog = false },
            onSave = { updated ->
                viewModel.updateEvent(updated)
                showEditDialog = false
            }
        )
    }
}

@Composable
fun EditEventDialog(
    event: EventItem,
    onDismiss: () -> Unit,
    onSave: (EventItem) -> Unit
) {

    var title by remember { mutableStateOf(event.title) }
    var description by remember { mutableStateOf(event.description) }
    var date by remember { mutableStateOf(event.date) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Event") },

        text = {
            Column {

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )

                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Date") }
                )
            }
        },

        confirmButton = {
            Button(onClick = {

                val updated = event.copy(
                    title = title,
                    description = description,
                    date = date
                )

                onSave(updated)

            }) {
                Text("Save")
            }
        },

        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    SacredHeartAjekarTheme {
        EventsScreen()
    }
}