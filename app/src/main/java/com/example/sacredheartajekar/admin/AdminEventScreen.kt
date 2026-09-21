package com.example.sacredheartajekar.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sacredheartajekar.viewmodel.EventViewModel

@Composable
fun AdminEventScreen(
    onBack: () -> Unit,
    viewModel: EventViewModel = viewModel()
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    var titleError by remember { mutableStateOf<String?>(null) }
    var descError by remember { mutableStateOf<String?>(null) }
    var dateError by remember { mutableStateOf<String?>(null) }

    val isPosting = viewModel.isPosting
    val message = viewModel.message

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // 🔙 Back Button
        TextButton(onClick = onBack) {
            Text("← Back")
        }

        Text(
            text = "Add Event",
            style = MaterialTheme.typography.headlineMedium
        )

        // 📌 Title
        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
                titleError = null
            },
            label = { Text("Event Title") },
            isError = titleError != null,
            modifier = Modifier.fillMaxWidth()
        )

        titleError?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        // 📌 Description
        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
                descError = null
            },
            label = { Text("Description") },
            isError = descError != null,
            modifier = Modifier.fillMaxWidth()
        )

        descError?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        // 📌 Date
        OutlinedTextField(
            value = date,
            onValueChange = {
                date = it
                dateError = null
            },
            label = { Text("Date (dd-MM-yyyy)") },
            isError = dateError != null,
            modifier = Modifier.fillMaxWidth()
        )

        dateError?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        // 📌 Add Event Button
        Button(
            onClick = {

                var valid = true

                if (title.trim().isEmpty()) {
                    titleError = "Title required"
                    valid = false
                }

                if (description.trim().isEmpty()) {
                    descError = "Description required"
                    valid = false
                }

                if (date.trim().isEmpty()) {
                    dateError = "Date required"
                    valid = false
                }

                if (valid) {
                    viewModel.addEvent(
                        title = title.trim(),
                        description = description.trim(),
                        date = date.trim()
                    )

                    // reset fields
                    title = ""
                    description = ""
                    date = ""
                }
            },
            enabled = !isPosting,
            modifier = Modifier.fillMaxWidth()
        ) {

            if (isPosting) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("Add Event")
            }
        }

        // 📌 Result Message
        message?.let {
            Text(it)
        }
    }
}