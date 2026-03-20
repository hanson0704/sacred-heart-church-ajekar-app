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
fun SCCScreen(onBack: () -> Unit) {

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
                text = "Small Christian Community",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = """
The Small Christian Community (SCC) is a vital part of parish life, bringing together families and individuals in small groups to grow in faith and fellowship.

In Sacred Heart Church, Ajekar, the parish is divided into 10 wards. Each ward gathers once a month in the homes of parishioners for prayer, reflection on the Word of God, and community bonding.

These gatherings help strengthen unity among members, encourage active participation, and create a sense of belonging within the parish. Families take turns hosting these prayer meetings, making the SCC a shared and meaningful spiritual experience.

Through the SCC, parishioners support one another, grow in faith, and live out Christian values in their daily lives with love, service, and compassion.
            """.trimIndent(),
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.4
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SCCScreenPreview(){
    SCCScreen(
        onBack = {}
    )
}