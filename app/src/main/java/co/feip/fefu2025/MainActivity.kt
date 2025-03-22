package co.feip.fefu2025

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.feip.fefu2025.ui.theme.AndroidRepoTheme
import kotlinx.datetime.LocalDate


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewRepositoryScreen()
        }
    }
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewRepositoryScreen() {
    AndroidRepoTheme {
        Scaffold(
            topBar = {
                ButtonTopBar("android-repo")
            }) { innerPadding ->
            RepositoryInformation(
                name = "android-repo",
                description = "Repository for homework on android studio.",
                forkNumber = 13000,
                starNumber = 31500,
                date = LocalDate(2025, 3, 3),
                languages = arrayOf("C++", "Python", "Kotlin", "Java", "Lua"),
                icon = R.drawable.ic_launcher_foreground,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }

    }
}
