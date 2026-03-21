package com.example.sacredheartajekar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.sacredheartajekar.model.NewsItem
import com.example.sacredheartajekar.viewmodel.MassViewModel
import com.example.sacredheartajekar.ui.theme.SacredHeartAjekarTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNewsClick: () -> Unit,
    onContactClick: () -> Unit,
    onMassTimingsClick: () -> Unit,
    onAboutUsClick: () -> Unit,
    latestAnnouncement: NewsItem?,
    onAdminClick: () -> Unit,
    isAdmin: Boolean,
    onEditMassClick: () -> Unit
) {

    val massViewModel: MassViewModel = viewModel()

    // ✅ FIXED STATE OBSERVATION
    val firebaseMass by massViewModel.masses

    // ✅ SAFE MASS LOGIC
    val finalMassList =
        if (firebaseMass.isNotEmpty()) firebaseMass
        else getDefaultMass()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // 🔝 HEADER
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Welcome")
                Text("Sacred Heart of Jesus Church", fontWeight = FontWeight.Bold)
                Text("Ajekar", color = MaterialTheme.colorScheme.primary)
            }

            IconButton(onClick = onAdminClick) {
                Icon(Icons.Default.AdminPanelSettings, contentDescription = "Admin Login")
            }
        }

        // 🔥 TODAY MASS
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Today's Mass", fontWeight = FontWeight.Bold)

                    if (isAdmin) {
                        IconButton(onClick = onEditMassClick) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit Mass")
                        }
                    }
                }

                // ✅ SAFE LIST DISPLAY
                if (finalMassList.isEmpty()) {

                    Text(
                        text = "No Mass Today",
                        style = MaterialTheme.typography.bodyMedium
                    )

                } else {

                    finalMassList.forEach { mass ->

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column {
                                Text(mass.title)

                                if (mass.note.isNotBlank()) {
                                    Text(
                                        mass.note,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Schedule, contentDescription = null)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(mass.time, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                Button(
                    onClick = onMassTimingsClick,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("View Full Schedule")
                }
            }
        }

        // 📢 ANNOUNCEMENT
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text("Latest Announcement", fontWeight = FontWeight.Bold)

                Text(
                    latestAnnouncement?.title ?: "No announcements available"
                )

                latestAnnouncement?.date?.let {
                    Text(it)
                }

                Button(onClick = onNewsClick) {
                    Text("View All Updates")
                }
            }
        }

        // 🔽 QUICK ACCESS
        Text("Quick Access", fontWeight = FontWeight.Bold)

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PremiumHomeActionCard("About Us", Icons.Default.Info, Modifier.weight(1f), onAboutUsClick)
            PremiumHomeActionCard("Mass Timings", Icons.Default.Schedule, Modifier.weight(1f), onMassTimingsClick)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PremiumHomeActionCard("News", Icons.AutoMirrored.Filled.Article, Modifier.weight(1f), onNewsClick)
            PremiumHomeActionCard("Contact", Icons.Default.Call, Modifier.weight(1f), onContactClick)
        }
    }
}

@Composable
fun PremiumHomeActionCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(124.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(shape = CircleShape) {
                Icon(icon, contentDescription = null, modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(title, textAlign = TextAlign.Center)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SacredHeartAjekarTheme {
        HomeScreen(
            onNewsClick = {},
            onContactClick = {},
            onMassTimingsClick = {},
            onAboutUsClick = {},
            latestAnnouncement = NewsItem(
                "Sample Announcement",
                "20-03-2026",
                "announcement"
            ),
            onAdminClick = {},
            isAdmin = true,
            onEditMassClick = {}
        )
    }
}