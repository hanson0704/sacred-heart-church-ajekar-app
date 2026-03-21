package com.example.sacredheartajekar

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sacredheartajekar.ui.theme.SacredHeartAjekarTheme
import androidx.core.net.toUri


@Composable
fun ContactScreen() {
    val phoneNumber = "+919141031601"
    val email = "sacredheartofjesuschurchajekar@gmail.com"
    val context = LocalContext.current
//    val latitude = 13.321964465044813
//    val longitude = 74.99577431977416
    val mapUrl = "https://www.google.com/maps/place/Sacred+Heart+of+Jesus+Church,+Ajekar/@13.3219798,74.995604,21z/data=!4m6!3m5!1s0x3bbb59d9b6ad02a5:0x7f9c355d5796fb0!8m2!3d13.3219651!4d74.9957747!16s%2Fg%2F11pv_5hf3m?entry=ttu&g_ep=EgoyMDI2MDEyMS4wIKXMDSoASAFQAw%3D%3D"
//    val placeLabel = "Sacred Heart of Jesus Church, Ajekar"


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Contact Us",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )


        Text(
            text = "Church Information",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align (Alignment.CenterHorizontally)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ContactRow(
                    icon = Icons.Filled.LocationOn,
                    label = "Address",
                    value = "Sacred Heart of Jesus Church, Ajekar"
                )

                ContactRow(
                    icon = Icons.Filled.Phone,
                    label = "Phone",
                    value = phoneNumber
                )

                ContactRow(
                    icon = Icons.Filled.Email,
                    label = "Email",
                    value = email
                )

                ContactRow(
                    icon = Icons.Filled.AccessTime,
                    label = "Office Hours",
                    value = "9:00 AM - 5:00 PM"
                )

            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val mapIntent = Intent(Intent.ACTION_VIEW, mapUrl.toUri())
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)
                },
                modifier = Modifier.width(300.dp).align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.GpsFixed,
                    contentDescription = "Map",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("View on Map")
            }

            Button(
                onClick = {
                    val callIntent = Intent(
                        Intent.ACTION_DIAL,
                        "tel:$phoneNumber".toUri()
                    )
                    context.startActivity(callIntent)
                },
                modifier = Modifier.width(300.dp).align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "Call",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Call Now")
            }

            Button(
                onClick = {
                    val mailIntent = Intent(
                        Intent.ACTION_SENDTO,
                        "mailto:$email".toUri()
                    )
                    context.startActivity(mailIntent)

                },
                modifier = Modifier.width(300.dp).align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Mail",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Send Mail")
            }

        }
    }
}

@Composable
fun ContactRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ContactScreenPreview() {
    SacredHeartAjekarTheme {
        ContactScreen()
    }
}