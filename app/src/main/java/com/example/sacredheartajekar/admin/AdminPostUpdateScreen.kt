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
import com.example.sacredheartajekar.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPostUpdateScreen(
    onBack: () -> Unit,
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Post Update") },
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

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

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {

                OutlinedTextField(
                    value = selectedTypeDisplay.ifBlank { "Select Type" },
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Update Type") },
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
                        typeError = "Select type"
                        valid = false
                    }

                    if (valid) {
                        viewModel.postNews(trimmedTitle, selectedTypeValue)

                        title = ""
                        selectedTypeDisplay = ""
                        selectedTypeValue = ""
                    }
                },
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

            message?.let {
                Text(it)
            }
        }
    }
}