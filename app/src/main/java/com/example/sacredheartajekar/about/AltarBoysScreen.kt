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
fun AltarBoysScreen(onBack: () -> Unit) {

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
                text = "Altar Boys",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = """
The Altar Servers play a vital role in assisting the priest during the Holy Mass and other liturgical celebrations. They serve at the altar with devotion, discipline, and reverence, helping create a prayerful and organized environment during worship.

At Sacred Heart Church, Ajekar, the Altar Servers are trained to understand the significance of the liturgy and to carry out their duties with dedication. Their service includes assisting with the preparation of the altar, holding liturgical items, and supporting the smooth conduct of Mass.

Being an Altar Server is not only a responsibility but also a spiritual journey. It encourages young members of the parish to grow in faith, develop discipline, and actively participate in the life of the Church.

We welcome young boys who are interested in serving the Church and deepening their relationship with God to join this ministry.
            """.trimIndent(),
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.4
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AltarBoysScreenPreview(){
    AltarBoysScreen(
        onBack = {}
    )
}