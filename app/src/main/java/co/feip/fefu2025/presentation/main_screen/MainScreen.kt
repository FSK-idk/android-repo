package co.feip.fefu2025.presentation.main_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.ui.theme.AndroidRepoTheme
import kotlin.math.min

@Composable
fun MainScreenRoot(
    viewModel: MainScreenViewModel,
    modifier: Modifier
) {
    val starredRepos = viewModel.starredRepos.collectAsState()
    val popularRepos = viewModel.popularRepos.collectAsState()

    Scaffold(
        topBar = {
            SearchTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    ) { innerPadding ->
        MainScreen(
            starredRepos = starredRepos.value,
            popularRepos = popularRepos.value,
            formatDecimal = viewModel::formatDecimal,
            modifier = modifier.padding(innerPadding),
        )
    }
}

@Composable
fun MainScreen(
    starredRepos: Array<Repo>,
    popularRepos: Array<Repo>,
    formatDecimal: (Int) -> String,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            item {
                Text(
                    "My stars",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(
                        count = min(10, starredRepos.size)
                    ) {
                        RepoCard(
                            starredRepos[it],
                            formatDecimal = formatDecimal,
                            modifier = Modifier
                                .size(width = 300.dp, height = 150.dp)
                        )
                    }
                }
            }

            item {
                Text(
                    "Popular",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
                )
            }

            items(count = popularRepos.size) {
                RepoCard(
                    popularRepos[it],
                    formatDecimal = formatDecimal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewUserScreen() {
    val context = LocalContext.current

    val starredRepos = Array<Repo>(20) {
        Repo(
            name = "android-repo",
            description = "Repository for homework on android studio.",
            starNumber = 31500,
            forkNumber = 13000,
            icon = R.drawable.ic_launcher_foreground,
        )
    }
    val popularRepos = Array<Repo>(20) {
        Repo(
            name = "android-repo",
            description = "Repository for homework on android studio.",
            starNumber = 31500,
            forkNumber = 13000,
            icon = R.drawable.ic_launcher_foreground,
        )
    }
    val formatDecimal = { number: Int -> FormatDecimalUseCase(context)(number) }

    AndroidRepoTheme {
        Scaffold(
            topBar = {
                SearchTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }) { innerPadding ->
            MainScreen(
                starredRepos = starredRepos,
                popularRepos = popularRepos,
                formatDecimal = formatDecimal,
                Modifier.padding(innerPadding),
            )
        }
    }
}