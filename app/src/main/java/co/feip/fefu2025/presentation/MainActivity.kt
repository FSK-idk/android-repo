package co.feip.fefu2025.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.main_screen.MainScreenRoot
import co.feip.fefu2025.presentation.main_screen.MainScreenViewModel
import co.feip.fefu2025.presentation.repo_screen.RepoScreenRoot
import co.feip.fefu2025.presentation.repo_screen.RepoScreenViewModel
import co.feip.fefu2025.ui.theme.AndroidRepoTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
//    private val vm by viewModel<MainScreenViewModel>()
    private val vm by viewModel<RepoScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidRepoTheme {
//                MainScreenRoot(vm, Modifier)
                RepoScreenRoot(vm, Modifier)
            }
        }
    }
}