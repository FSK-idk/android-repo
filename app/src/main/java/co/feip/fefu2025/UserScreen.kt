package co.feip.fefu2025

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.ui.theme.AndroidRepoTheme
import kotlin.math.min


@Composable
fun UserScreen(
    starredRepositoryCardsData: Array<RepositoryCardData>,
    recommendedRepositoryCardsData: Array<RepositoryCardData>,
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
                        count = min(10, starredRepositoryCardsData.size)
                    ) {
                        RepositoryCard(
                            starredRepositoryCardsData[it],
                            Modifier.size(width = 300.dp, height = 150.dp)
                        )
                    }
                }
            }

            item {
                Text(
                    "Recommended",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
                )
            }

            items(count = recommendedRepositoryCardsData.size) {
                RepositoryCard(
                    recommendedRepositoryCardsData[it],
                    Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
        }
    }
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewUserScreen() {
    val starred = Array<RepositoryCardData>(20) {
        RepositoryCardData(
            name = "android-repo",
            description = "Repository for homework on android studio.",
            starNumber = 31500,
            forkNumber = 13000,
            icon = R.drawable.ic_launcher_foreground,
        )
    }
    val recommended = Array<RepositoryCardData>(20) {
        RepositoryCardData(
            name = "android-repo",
            description = "Repository for homework on android studio.",
            starNumber = 31500,
            forkNumber = 13000,
            icon = R.drawable.ic_launcher_foreground,
        )
    }

    AndroidRepoTheme {
        Scaffold(
            topBar = {
                SearchTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }) {
            UserScreen(
                starredRepositoryCardsData = starred,
                recommendedRepositoryCardsData = recommended,
                Modifier
                    .padding(it)
            )
        }

    }
}
