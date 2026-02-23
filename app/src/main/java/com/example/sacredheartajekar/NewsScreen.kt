package com.example.sacredheartajekar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
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

        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredNews) { news ->
                NewsCard(news)
            }
        }
    }
}

@Composable
fun NewsCard(news: NewsItem) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = news.title, fontWeight = FontWeight.Bold)
            Text(text = "Date: ${news.date}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsScreenPreview() {
    SacredHeartAjekarTheme {
        NewsScreen()
    }
}
