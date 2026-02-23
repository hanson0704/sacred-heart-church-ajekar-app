package com.example.sacredheartajekar.about

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(
    onParishHistoryClick: () -> Unit,
    onAssociationsClick: () -> Unit,
    onPastoralClick: () -> Unit,
    onAltarBoysClick: () -> Unit,
    onMarianClick: () -> Unit,
    onYouthClick: () -> Unit,
    onSccClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Text(
            text = "About Us",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )
        AboutItem(
            title = "Parish History",
            preview = "Discover the origin and growth of our parish through the years.",
            onClick = onParishHistoryClick
        )

        AboutItem(
            title = "Associations",
            preview = "Explore the various parish associations and their activities.",
            onClick = onAssociationsClick
        )

        AboutItem(
            title = "Pastoral Commissions",
            preview = "Learn about the commissions guiding the parish ministries.",
            onClick = onPastoralClick
        )

        AboutItem(
            title = "Altar Boys",
            preview = "Meet the young servers assisting in liturgical celebrations.",
            onClick = onAltarBoysClick
        )

        AboutItem(
            title = "Marian Sodality",
            preview = "A devotional group dedicated to Mother Mary.",
            onClick = onMarianClick
        )

        AboutItem(
            title = "ICYM / YCS",
            preview = "Faith-sharing groups strengthening parish unity.",
            onClick = onYouthClick
        )

        AboutItem(
            title = "Small Christian Community",
            preview = "Faith-sharing groups strengthening parish unity.",
            onClick = onSccClick
        )

    }
}


@Composable
fun AboutItem(title: String, preview: String, onClick: ()-> Unit){
    Card(
        modifier = Modifier.fillMaxWidth().clickable{onClick()}
    ) {
        Row(
            modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = preview,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Open"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(
        onParishHistoryClick = {},
        onAssociationsClick = {},
        onPastoralClick = {},
        onAltarBoysClick = {},
        onMarianClick = {},
        onYouthClick = {},
        onSccClick = {}
    )
}
