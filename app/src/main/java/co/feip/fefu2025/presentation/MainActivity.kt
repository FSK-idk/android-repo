package co.feip.fefu2025.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import co.feip.fefu2025.nav.AppNavHost
import co.feip.fefu2025.ui.theme.AndroidRepoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidRepoTheme {
                val navController = rememberNavController()
                AppNavHost(navHostController = navController)
            }
        }
    }
}