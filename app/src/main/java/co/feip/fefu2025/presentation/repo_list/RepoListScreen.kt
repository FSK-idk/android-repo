package co.feip.fefu2025.presentation.repo_list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.presentation.shared.RepoCard
import co.feip.fefu2025.ui.theme.AndroidRepoTheme
import kotlin.math.min

@Composable
fun RepoListScreen(
    modifier: Modifier = Modifier,
    starredRepos: List<Repo>,
    popularRepos: List<Repo>,
    formatDecimal: (Int) -> String,
    onStarredClick: () -> Unit,
    onRepoClick: (Int) -> Unit,
) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp)
                        .clickable(onClick = onStarredClick),
                    text = stringResource(R.string.Starred),
                    fontWeight = FontWeight.Bold,
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(count = min(10, starredRepos.size)) {
                        RepoCard(
                            modifier = Modifier
                                .size(width = 300.dp, height = 150.dp),
                            repo = starredRepos[it],
                            formatDecimal = formatDecimal,
                            onClick = onRepoClick,
                        )
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp),
                    text = stringResource(R.string.Popular),
                    fontWeight = FontWeight.Bold,
                )
            }

            items(count = popularRepos.size) {
                RepoCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    repo = popularRepos[it],
                    formatDecimal = formatDecimal,
                    onClick = onRepoClick,
                )
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun RepoListScreenPreview() {
    val starredRepos = List<Repo>(20) { index ->
        Repo(
            id = index,
            name = "android-repo",
            description = "Repository for homework on android studio.",
            starNumber = 31500,
            forkNumber = 13000,
            icon = R.drawable.ic_launcher_foreground,
        )
    }
    val popularRepos = List<Repo>(20) { index ->
        Repo(
            id = index,
            name = "android-repo",
            description = "Repository for homework on android studio.",
            starNumber = 31500,
            forkNumber = 13000,
            icon = R.drawable.ic_launcher_foreground,
        )
    }

    AndroidRepoTheme {
        Surface {
            RepoListScreen(
                starredRepos = starredRepos,
                popularRepos = popularRepos,
                formatDecimal = FormatDecimalUseCase(LocalContext.current)::invoke,
                onStarredClick = {},
                onRepoClick = {},
            )
        }
    }
}