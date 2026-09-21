package com.example.sacredheartajekar

import com.example.sacredheartajekar.model.MassInfo
import java.util.Calendar

fun getDefaultMass(): List<MassInfo> {

    return try {

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)

        when (day) {

            Calendar.MONDAY,
            Calendar.TUESDAY,
            Calendar.WEDNESDAY -> listOf(
                MassInfo(
                    id = "1",
                    title = "Weekday Mass",
                    time = "7:00 AM",
                    note = ""
                )
            )

            Calendar.THURSDAY -> listOf(
                MassInfo(
                    id = "1",
                    title = "Weekday Mass",
                    time = "7:00 AM",
                    note = "St. Xavier Convent, Ajekar"
                )
            )

            Calendar.FRIDAY -> listOf(
                MassInfo(
                    id = "1",
                    title = "Evening Mass",
                    time = "4:30 PM",
                    note = "Sacred Heart Church – Novena"
                )
            )

            Calendar.SATURDAY -> listOf(
                MassInfo(
                    id = "1",
                    title = "Weekday Mass",
                    time = "7:00 AM",
                    note = ""
                )
            )

            Calendar.SUNDAY -> listOf(
                MassInfo(
                    id = "1",
                    title = "Morning Mass",
                    time = "8:00 AM",
                    note = ""
                ),
                MassInfo(
                    id = "2",
                    title = "Children Mass",
                    time = "10:30 AM",
                    note = ""
                )
            )

            else -> listOf(
                MassInfo(
                    id = "1",
                    title = "Mass",
                    time = "7:00 AM",
                    note = ""
                )
            )
        }

    } catch (e: Exception) {

        // 🔴 SAFETY FALLBACK (prevents blank screen crash)
        listOf(
            MassInfo(
                id = "1",
                title = "Mass",
                time = "7:00 AM",
                note = ""
            )
        )
    }
}