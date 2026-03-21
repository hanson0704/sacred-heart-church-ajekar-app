package com.example.sacredheartajekar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sacredheartajekar.model.NewsItem
import com.example.sacredheartajekar.viewmodel.NewsViewModel
import com.example.sacredheartajekar.ui.theme.SacredHeartAjekarTheme

@Composable
fun NewsScreen() {

    val viewModel: NewsViewModel = viewModel()
    val announcements by viewModel.announcements.collectAsState()

    val tabs = listOf("Announcements", "Obituary", "Bulletin")
    var selectedTab by remember { mutableIntStateOf(0) }

    val filteredNews = when (selectedTab) {
        0 -> announcements.filter { it.type == "announcement" }
        1 -> announcements.filter { it.type == "obituary" }
        else -> announcements.filter { it.type == "bulletin" }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SecondaryTabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title, maxLines = 1, softWrap = false) }
                )
            }
        }

        if (filteredNews.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {

                Column(
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {

                    val message = when (selectedTab) {
                        0 -> "No Announcements Yet 📢"
                        1 -> "No Obituaries Available 🕊️"
                        else -> "No Bulletins Available 📰"
                    }

                    val subMessage = when (selectedTab) {
                        0 -> "Announcements will appear here once posted"
                        1 -> "Obituary updates will be shown here"
                        else -> "Bulletins will be available here"
                    }

                    Text(
                        text = message,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.padding(6.dp))

                    Text(
                        text = subMessage,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredNews) { news ->
                    NewsCard(news, viewModel)
                }
            }
        }
    }
}

@Composable
fun NewsCard(news: NewsItem, viewModel: NewsViewModel) {

    val isAdmin =
        com.google.firebase.auth.FirebaseAuth.getInstance().currentUser != null

    var showEditDialog by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth()) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(text = news.title, fontWeight = FontWeight.Bold)

            Text(text = "Date: ${news.date}")

            if (isAdmin) {

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                    Button(onClick = { showEditDialog = true }) {
                        Text("Edit")
                    }

                    Button(onClick = { viewModel.deleteNews(news.id) }) {
                        Text("Delete")
                    }

                }
            }
        }
    }

    if (showEditDialog) {
        EditNewsDialog(
            news = news,
            onDismiss = { showEditDialog = false },
            onSave = { updated ->
                viewModel.updateNews(updated)
                showEditDialog = false
            }
        )
    }
}

@Composable
fun EditNewsDialog(
    news: NewsItem,
    onDismiss: () -> Unit,
    onSave: (NewsItem) -> Unit
) {

    var title by remember { mutableStateOf(news.title) }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = { Text("Edit Update") },

        text = {

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") }
            )

        },

        confirmButton = {

            Button(
                onClick = {

                    val updatedNews = news.copy(
                        title = title
                    )

                    onSave(updatedNews)
                }
            ) {
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
fun NewsScreenPreview() {
    SacredHeartAjekarTheme {
        NewsScreen()
    }
}
