package com.example.sacredheartajekar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.sacredheartajekar.ui.theme.SacredHeartAjekarTheme

@Composable
fun GalleryScreen() {
    ScreenTemplate("Gallery Screen")
}

@Preview(showBackground = true)
@Composable
fun GalleryScreenPreview() {
    SacredHeartAjekarTheme {
        GalleryScreen()
    }
}