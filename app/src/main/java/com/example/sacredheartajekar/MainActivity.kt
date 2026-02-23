package com.example.sacredheartajekar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.sacredheartajekar.ui.theme.SacredHeartAjekarTheme
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sacredheartajekar.about.*
import com.example.sacredheartajekar.viewmodel.NewsViewModel
import com.example.sacredheartajekar.admin.AdminLoginScreen
import com.example.sacredheartajekar.admin.AdminPanelScreen
import com.example.sacredheartajekar.model.NewsItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        FirebaseMessaging.getInstance().subscribeToTopic("parish_updates")

        setContent {
            SacredHeartAjekarTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()


    // 🔥 Shared ViewModel
    val newsViewModel: NewsViewModel = viewModel()
    val announcements by newsViewModel.announcements.collectAsState()

    // Latest announcement for HomeScreen
    val latestAnnouncement = announcements.firstOrNull()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("home") {
                HomeScreen(
                    onNewsClick = { navController.navigate("news") },
                    onContactClick = { navController.navigate("contact") },
                    onMassTimingsClick = { navController.navigate("mass_timings") },
                    onAboutUsClick = { navController.navigate("about") },
                    latestAnnouncement = latestAnnouncement,
                    onAdminClick = { navController.navigate("admin_login") },
                )
            }

            // ✅ Firebase-driven NewsScreen
            composable("news") { NewsScreen() }

            composable("events") { EventsScreen() }
            composable("gallery") { GalleryScreen() }
            composable("contact") { ContactScreen() }
            composable("mass_timings") { MassTimings() }

            // About navigation stays same
            composable("about") {
                AboutScreen(
                    onParishHistoryClick = { navController.navigate("parish_history") },
                    onAssociationsClick = { navController.navigate("associations") },
                    onPastoralClick = { navController.navigate("pastoral_commissions") },
                    onAltarBoysClick = { navController.navigate("altar_boys") },
                    onMarianClick = { navController.navigate("marian_sodality") },
                    onYouthClick = { navController.navigate("youth") },
                    onSccClick = { navController.navigate("scc") }
                )
            }

            composable("parish_history") {
                ParishHistoryScreen(onBack = { navController.popBackStack() })
            }
            composable("associations") {
                AssociationsScreen(onBack = { navController.popBackStack() })
            }
            composable("pastoral_commissions") {
                PastoralCommissionsScreen(onBack = { navController.popBackStack() })
            }
            composable("altar_boys") {
                AltarBoysScreen(onBack = { navController.popBackStack() })
            }
            composable("marian_sodality") {
                MarianSodalityScreen(onBack = { navController.popBackStack() })
            }
            composable("youth") {
                YouthOrganizationsScreen(onBack = { navController.popBackStack() })
            }
            composable("scc") {
                SCCScreen(onBack = { navController.popBackStack() })
            }
            composable("admin_login") {
                val user = FirebaseAuth.getInstance().currentUser

                if (user != null) {
                    // Already logged in
                    LaunchedEffect(Unit) {
                        navController.navigate("admin_panel") {
                            popUpTo("admin_login") { inclusive = true }
                        }
                    }
                } else {
                    AdminLoginScreen(
                        onLoginSuccess = {
                            navController.navigate("admin_panel") {
                                popUpTo("admin_login") { inclusive = true }
                            }
                        }
                    )
                }
            }
            composable("admin_panel") {
                AdminPanelScreen(
                    onLogout = {
                        FirebaseAuth.getInstance().signOut()

                        navController.navigate("home") {
                            popUpTo("admin_panel") { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}



@Composable
fun BottomNavBar(navController: NavController) {

    val items = listOf(
        Triple("home", "Home", Icons.Filled.Home),
        Triple("news", "News", Icons.AutoMirrored.Filled.Article),
        Triple("events", "Events", Icons.Filled.Event),
        Triple("gallery", "Gallery", Icons.Filled.Image),
        Triple("contact", "Contact", Icons.Filled.Call)
    )

    NavigationBar {

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        items.forEach { (route, label, icon) ->
            NavigationBarItem(
                selected = currentRoute == route,
                onClick = {
                    navController.navigate(route) {
                        popUpTo("home") { inclusive = false }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label
                    )
                },
                label = { Text(label) }
            )
        }
    }
}


@Composable
fun ScreenTemplate(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    SacredHeartAjekarTheme {

        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavBar(navController)
            }
        ) { innerPadding ->

            HomeScreen(
                modifier = Modifier.padding(innerPadding), // ⭐ FIX
                onNewsClick = {},
                onContactClick = {},
                onMassTimingsClick = {},
                onAboutUsClick = {},
                latestAnnouncement = NewsItem(
                    "Choir Practice for Feast",
                    "19-02-2026",
                    "announcement"
                ),
                onAdminClick = {}
            )
        }
    }
}