package co.feip.fefu2025.presentation.repo_list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.presentation.shared.PageControl
import co.feip.fefu2025.presentation.shared.RepoCard
import co.feip.fefu2025.ui.theme.AndroidRepoTheme

@Composable
fun RepoListScreen(
    modifier: Modifier = Modifier,
    scrollState: LazyListState,
    starredRepos: List<Repo>,
    popularRepos: List<Repo>,
    pageNumber: Int,
    formatDecimal: (Int) -> String,
    onStarredClick: () -> Unit,
    onRepoClick: (Int) -> Unit,
    onTopClick: () -> Unit,
    onFirstPageClick: () -> Unit,
    onPrevPageClick: () -> Unit,
    onNextPageClick: () -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        state = scrollState,
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, start = 10.dp, end = 10.dp)
                    .clickable(onClick = onStarredClick),
                text = stringResource(R.string.Starred),
                fontWeight = FontWeight.Bold,
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(start = 10.dp, end = 10.dp)
            ) {
                items(count = starredRepos.size) {
                    RepoCard(
                        modifier = Modifier.size(width = 300.dp, height = 150.dp),
                        repo = starredRepos[it],
                        formatDecimal = formatDecimal,
                        onClick = onRepoClick,
                    )
                }
            }
        }

        item {
            Text(
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
                text = stringResource(R.string.Popular),
                fontWeight = FontWeight.Bold,
            )
        }

        items(count = popularRepos.size) {
            RepoCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(start = 10.dp, end = 10.dp),
                repo = popularRepos[it],
                formatDecimal = formatDecimal,
                onClick = onRepoClick,
            )
        }

        item {
            PageControl(
                pageNumber = pageNumber,
                onTopClick = onTopClick,
                onFirstPageClick = onFirstPageClick,
                onPrevPageClick = onPrevPageClick,
                onNextPageClick = onNextPageClick,
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun RepoListScreenPreview() {
    val scrollState = rememberLazyListState()
    val starredRepos = List<Repo>(20) { index ->
        Repo(
            id = index,
            name = "android-repo",
            description = "Repository for homework on android studio.",
            starNumber = 31500,
            forkNumber = 13000,
            iconUrl = "",
        )
    }
    val popularRepos = List<Repo>(1) { index ->
        Repo(
            id = index,
            name = "android-repo",
            description = "Repository for homework on android studio.",
            starNumber = 31500,
            forkNumber = 13000,
            iconUrl = "",
        )
    }
    val pageNumber = 24

    AndroidRepoTheme {
        Surface {
            RepoListScreen(
                scrollState = scrollState,
                starredRepos = starredRepos,
                popularRepos = popularRepos,
                pageNumber = pageNumber,
                formatDecimal = { it.toString() },
                onStarredClick = {},
                onRepoClick = {},
                onTopClick = {},
                onFirstPageClick = {},
                onPrevPageClick = {},
                onNextPageClick = {},
            )
        }
    }
}