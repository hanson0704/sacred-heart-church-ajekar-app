package com.example.sacredheartajekar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sacredheartajekar.ui.theme.SacredHeartAjekarTheme

@Composable
fun MassTimings() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // Page Title
        Text(
            text = "Mass Timings",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )

        // Weekdays
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                SectionHeader(
                    icon = Icons.Filled.Event,
                    title = "Weekdays"
                )

                MassRow(
                    day = "Monday – Wednesday",
                    time = "7:00 AM"
                )

                MassRow(
                    day = "Thursday",
                    time = "7:00 AM",
                    note = "St. Xavier Convent, Ajekar"
                )

                MassRow(
                    day = "Friday",
                    time = "4:30 PM",
                    note = "Sacred Heart of Jesus Church – Novena"
                )

                MassRow(
                    day = "Saturday",
                    time = "7:00 AM"
                )
            }
        }

   //Sunday
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                SectionHeader(
                    icon = Icons.Filled.EventAvailable,
                    title = "Sunday"
                )

                MassRow(
                    day = "Morning Mass",
                    time = "8:00 AM"
                )

                MassRow(
                    day = "Children Mass",
                    time = "10:30 AM"
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)


        ) {
            Text(
                text = "If there are any changes in the Mass timings, " +
                        "they will be shown in the Announcements " +
                        "section of the app and will also be announced" +
                        " on Sunday at both Masses by the Parish Priest " +
                        "or, in his absence, by the Celebrant Priest",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "ಮಿಸಾಚ್ಯಾ ವೆಳಾ ವಕತಾಂತ್ ಕಿತೆಂಯಿ ಬದಾಲಾವಣ್ ಆಸ್ಲ್ಯಾರ್, " +
                        "ತೆಂ ಆಪ್ (App) ಹಾಚ್ಯಾ 'ಅನೌನ್ಸ್\u200Cಮೆಂಟ್' (Announcements) ವಿಭಾಗಾಂತ್ " +
                        "ಪಳೆಂವ್ಕ್ ಮೆಳ್ತಾ ಆನಿ ಆಯ್ತಾರಾಚ್ಯಾ ದೋನ್-ಯಿ ಮಿಸಾಂ ವೆಳಾರ್ ವಿಗಾರ್ ಬಾಪಾಂ " +
                        "ಕಡ್ಲ್ಯಾನ್ ಯಾ ತಾಂಚ್ಯಾ ಗೈರ್-ಹಾಜ್ರೆಂತ್ ಮಿಸಾಚ್ಯಾ ಪ್ರಧಾನ್ ಯಾಜಕಾ" +
                        " ಕಡ್ಲ್ಯಾನ್ ತಿಳ್ಸುಂಚೆಂ ಆಸ್ತಲೆಂ.",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
@Composable
fun SectionHeader(
    icon: ImageVector,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun MassRow(
    day: String,
    time: String,
    note: String? = null
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = day,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AccessTime,
                    contentDescription = "Time",
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = time,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (note != null) {
            Text(
                text = note,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MassTimingsPreview() {
    SacredHeartAjekarTheme {
        MassTimings()
    }
}