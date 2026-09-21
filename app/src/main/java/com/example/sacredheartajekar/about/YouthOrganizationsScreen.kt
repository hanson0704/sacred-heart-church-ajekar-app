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
fun YouthOrganizationsScreen(onBack: () -> Unit) {

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
                text = "ICYM / YCS",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = """
The youth of Sacred Heart Church, Ajekar actively participate in parish life through two important organizations: ICYM (Indian Catholic Youth Movement) and YCS (Young Christian Students). These groups provide a strong foundation for young members to grow in faith, leadership, and service.

Indian Catholic Youth Movement (ICYM)
ICYM focuses on the overall development of youth through spiritual growth, leadership, and social responsibility. Members actively participate in prayer meetings, retreats, parish feasts, cultural programs, and social outreach activities. It encourages teamwork, confidence, and active involvement in parish life.

Through ICYM, young people are given opportunities to take initiative, organize events, and contribute meaningfully to the Church. It helps them become responsible leaders rooted in Christian values and committed to serving society.

Young Christian Students (YCS)
YCS is centered on guiding students to live Christian values in their academic and daily lives. It promotes discipline, reflection, and moral responsibility through regular meetings, discussions, and faith-based activities.

YCS encourages students to balance their studies with spiritual growth, helping them make value-based decisions and live as true witnesses of Christ in their schools and communities. It also nurtures a sense of responsibility, integrity, and faith in young minds.

Together, ICYM and YCS play a vital role in shaping the youth of the parish. They help young members grow in faith, unity, and service, preparing them to actively participate in the life of the Church and contribute positively to society.
""".trimIndent(),
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.4
        )
    }
}

@Preview(showBackground = true)
@Composable
fun YouthOrganizationsScreenPreview(){
    YouthOrganizationsScreen(
        onBack = {}
    )
}