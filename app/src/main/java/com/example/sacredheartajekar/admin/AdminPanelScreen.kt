package com.example.sacredheartajekar.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sacredheartajekar.ui.theme.SacredHeartAjekarTheme
import com.example.sacredheartajekar.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPanelScreen(
    onLogout: () -> Unit,
    onAddEventClick: () -> Unit,// ⭐ navigation callback
    onAddGalleryClick: () -> Unit,
    viewModel: NewsViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var selectedTypeDisplay by remember { mutableStateOf("") }
    var selectedTypeValue by remember { mutableStateOf("") }

    var titleError by remember { mutableStateOf<String?>(null) }
    var typeError by remember { mutableStateOf<String?>(null) }

    val isPosting = viewModel.isPosting
    val message = viewModel.postMessage

    val types = listOf("Announcement", "Obituary", "Bulletin")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // 🔓 Logout
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onLogout) {
                Text("Logout")
            }
        }

        // 🏷 Title
        Text(
            text = "Parish Admin Panel",
            style = MaterialTheme.typography.headlineMedium
        )

        // 📌 Title Input
        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
                titleError = null
            },
            label = { Text("Title") },
            isError = titleError != null,
            modifier = Modifier.fillMaxWidth()
        )

        titleError?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        // 📌 Dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            OutlinedTextField(
                value = selectedTypeDisplay.ifBlank { "Select Update Type" },
                onValueChange = {},
                readOnly = true,
                label = { Text("Parish Update") },
                isError = typeError != null,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                types.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            selectedTypeDisplay = type
                            selectedTypeValue = type.lowercase()
                            typeError = null
                            expanded = false
                        }
                    )
                }
            }
        }

        typeError?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        // 📌 Post Button (News)
        Button(
            onClick = {
                val trimmedTitle = title.trim()

                titleError = null
                typeError = null

                var valid = true

                if (trimmedTitle.isEmpty()) {
                    titleError = "Title cannot be empty"
                    valid = false
                }

                if (selectedTypeValue.isBlank()) {
                    typeError = "Please select update type"
                    valid = false
                }

                if (valid) {
                    viewModel.postNews(trimmedTitle, selectedTypeValue)

                    title = ""
                    selectedTypeDisplay = ""
                    selectedTypeValue = ""
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
                Text("Post Update")
            }
        }

        // 📌 Result Message
        message?.let {
            Text(
                text = it,
                color = if (it.contains("success", true))
                    Color(0xFF2E7D32)
                else
                    MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔥 ADD EVENT BUTTON (NEW FEATURE)
        Button(
            onClick = onAddEventClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Event")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onAddGalleryClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Gallery Image")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminPanelPreview() {
    SacredHeartAjekarTheme {
        AdminPanelScreen(
            onLogout = {},
            onAddEventClick = {},
            onAddGalleryClick = {},
        )
    }
}