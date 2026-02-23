package com.example.sacredheartajekar.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ParishHistoryScreen(onBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }

            Text(
                text = "Parish History",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        // 📜 Article Content
        Text(
            text = """
Sacred Heart of Jesus Church, Ajekar has a rich and vibrant history that reflects the deep faith and unity of its parishioners. Established several decades ago, the parish began as a small faith community with a strong desire to grow spiritually and serve the surrounding villages.

Over the years, the parish has witnessed steady growth both in infrastructure and spiritual life. From humble beginnings with simple chapels and limited facilities, it has now evolved into a well-established parish that nurtures faith through liturgical celebrations, catechism, and social outreach programs.

The construction of the present church stands as a symbol of the dedication and sacrifices made by earlier generations. With the support of parish priests, religious sisters, and devoted laity, the church became a center of prayer, harmony, and service.

Apart from spiritual activities, the parish has played a significant role in education and social development. Institutions established under the parish have contributed greatly to the formation of young minds and the upliftment of the local community.

Today, Sacred Heart of Jesus Church continues to thrive as a beacon of faith, bringing together families from different areas into one united parish community. With a strong foundation rooted in tradition and a vision for the future, the parish remains committed to spreading the message of love, compassion, and service inspired by the Sacred Heart of Jesus.
            """.trimIndent(),
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.4
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ParishHistoryScreenPreview(){
    ParishHistoryScreen(
        onBack = {}
    )
}