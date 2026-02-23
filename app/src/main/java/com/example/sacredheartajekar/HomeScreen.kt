package com.example.sacredheartajekar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sacredheartajekar.ui.theme.SacredHeartAjekarTheme
import com.example.sacredheartajekar.model.NewsItem



@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNewsClick: () -> Unit,
    onContactClick: () -> Unit,
    onMassTimingsClick: () -> Unit,
    onAboutUsClick: () -> Unit,
    latestAnnouncement: NewsItem?,
    onAdminClick: ()-> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize().safeDrawingPadding().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onAdminClick) {
                Text("Admin")
            }
        }
        Text(
            "Sacred Heart of Jesus Church Ajekar",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            "Welcome to the Parish App",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Today's Mass",
                    fontWeight = FontWeight.Bold
                )
                Text("6:30 AM")

                Button(
                    onClick = {},
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("View Full Schedule")
                }
            }
        }

        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text("Latest Announcement", fontWeight = FontWeight.Bold)

                Text(
                    text = latestAnnouncement?.title ?: "Loading announcements...",
                    style = MaterialTheme.typography.bodyLarge
                )


                Text(
                    text = latestAnnouncement?.date ?: "",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { onNewsClick() }) {
                        Text("View All News")
                    }
                }
            }
        }

        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HomeButton(
                        "About Us",
                        modifier = Modifier.weight(1f),
                        onClick = {onAboutUsClick()}
                    )
                    HomeButton(
                        "Mass Timings",
                        modifier = Modifier.weight(1f),
                        onClick = {onMassTimingsClick()}
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HomeButton(
                        "News",
                        modifier = Modifier.weight(1f), onClick = {onNewsClick()}
                    )
                    HomeButton(
                        "Contact Us",
                        modifier = Modifier.weight(1f),
                        onClick = {onContactClick()}
                    )
                }
            }
        }

    }
}

@Composable
fun HomeButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = label,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SacredHeartAjekarTheme {
        HomeScreen(
            onNewsClick = {},
            onContactClick = {},
            onMassTimingsClick = {},
            onAboutUsClick = {},
            latestAnnouncement =  NewsItem("Preview Announcement","01-01-2026","announcement"),
            onAdminClick = {}
        )
    }
}
