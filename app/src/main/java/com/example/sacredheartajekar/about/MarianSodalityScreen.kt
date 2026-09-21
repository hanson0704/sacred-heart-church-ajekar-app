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
fun MarianSodalityScreen(onBack: () -> Unit) {

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
                text = "Marian Sodality",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = """
The Marian Sodality is a devoted group within the parish dedicated to honoring the Blessed Virgin Mary and growing in spiritual life through her example.

Members of the Marian Sodality strive to deepen their faith by participating in regular prayers, Rosary recitations, and Marian feasts. They play an active role in parish activities, fostering unity, service, and devotion among the faithful.

At Sacred Heart Church, Ajekar, the Marian Sodality encourages its members to imitate the virtues of Mother Mary—humility, obedience, purity, and unwavering faith in God.

Through prayer, service, and community involvement, the Sodality continues to inspire parishioners to live a Christ-centered life guided by the loving intercession of the Blessed Mother.
            """.trimIndent(),
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.4
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MarianSodalityScreenPreview(){
    MarianSodalityScreen(
        onBack = {}
    )
}