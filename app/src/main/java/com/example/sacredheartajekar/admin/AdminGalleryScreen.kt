package com.example.sacredheartajekar.admin

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.sacredheartajekar.viewmodel.GalleryViewModel

@Composable
fun AdminGalleryScreen(
    onBack: () -> Unit,
    viewModel: GalleryViewModel = viewModel()
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedImages by remember { mutableStateOf<List<Uri>>(emptyList()) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) {
        selectedImages = it
    }

    val isUploading = viewModel.isUploading
    val message = viewModel.message

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())   // ⭐ SCROLL ADDED
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // 🔙 Back
        TextButton(onClick = onBack) {
            Text("← Back")
        }

        // 🏷 Title
        Text(
            "Upload Gallery",
            style = MaterialTheme.typography.headlineMedium
        )

        // 📌 Title input
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        // 📌 Description input
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        // 📌 Select images
        Button(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select Images")
        }

        // 📌 Preview selected images
        selectedImages.forEach { uri ->
            AsyncImage(
                model = uri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
        }

        // 📌 Upload button
        Button(
            onClick = {
                viewModel.uploadGallery(title, description, selectedImages)
            },
            enabled = selectedImages.isNotEmpty() && !isUploading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isUploading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("Upload")
            }
        }

        // 📌 Message
        message?.let {
            Text(it)
        }
    }
}