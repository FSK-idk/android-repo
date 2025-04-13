package co.feip.fefu2025.presentation.starred_repo_list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.presentation.shared.RepoCard
import co.feip.fefu2025.ui.theme.AndroidRepoTheme

@Composable
fun StarredRepoListScreen(
    modifier: Modifier = Modifier,
    starredRepos: List<Repo>,
    formatDecimal: (Int) -> String,
    onRepoClick: (Int) -> Unit,
) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            items(count = starredRepos.size) {
                RepoCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    repo = starredRepos[it],
                    formatDecimal = formatDecimal,
                    onClick = onRepoClick,
                )
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun StarredRepoListScreenPreview() {
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

    AndroidRepoTheme {
        Surface {
            StarredRepoListScreen(
                starredRepos = starredRepos,
                formatDecimal = FormatDecimalUseCase(LocalContext.current)::invoke,
                onRepoClick = {},
            )
        }
    }
}