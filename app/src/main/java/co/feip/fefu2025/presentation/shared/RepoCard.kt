package co.feip.fefu2025.presentation.shared

import android.content.res.Configuration
import android.icu.text.CompactDecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.ui.theme.AndroidRepoTheme
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun RepoCard(
    modifier: Modifier,
    repo: Repo,
    formatDecimal: (Int) -> String,
    onClick: (Int) -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = { onClick(repo.id) },
    ) {
        Column(
            modifier = Modifier.Companion
                .clip(shape = RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.Companion.CenterVertically,
            ) {
                AsyncImage(
                    modifier = Modifier.size(30.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(repo.iconUrl)
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_launcher_foreground),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                Text(
                    text = repo.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Companion.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }

            Text(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .weight(1f),
                text = repo.description,
                overflow = TextOverflow.Companion.Ellipsis,
                fontSize = 12.sp,
                maxLines = 3,
                minLines = 3,
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.Companion.CenterVertically
            ) {
                Label(
                    text = formatDecimal(repo.starNumber),
                    imageVector = Icons.Filled.Star,
                )

                Label(
                    text = formatDecimal(repo.forkNumber),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_mdi_source_branch),
                )
            }
        }
    }
}

@Composable
fun Label(
    text: String,
    imageVector: ImageVector,
) {
    Icon(
        modifier = Modifier.Companion.size(15.dp),
        imageVector = imageVector,
        contentDescription = null,
    )

    Text(
        text = text,
        fontSize = 12.sp,
    )
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewRepositoryCard() {
    val context = LocalContext.current

    val repo = Repo(
        id = 0,
        name = "android-repo",
        description = "Repository for homework on android studio.",
        starNumber = 31500,
        forkNumber = 13000,
        iconUrl = "",
    )

    AndroidRepoTheme {
        Surface {
            RepoCard(
                modifier = Modifier.Companion.size(300.dp, 150.dp),
                repo = repo,
                formatDecimal = { it.toString() },
                onClick = {},
            )
        }
    }
}