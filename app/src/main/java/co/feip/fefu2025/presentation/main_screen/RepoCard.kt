package co.feip.fefu2025.presentation.main_screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.ui.theme.AndroidRepoTheme

@Composable
fun RepoCard(
    repo: Repo,
    formatDecimal: (Int) -> String,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.Companion
                .clip(shape = RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.Companion.CenterVertically,
            ) {
                Icon(
                    painterResource(repo.icon),
                    contentDescription = "",
                    modifier = Modifier.Companion.size(40.dp)
                )

                Text(
                    repo.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Companion.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }

            Text(
                repo.description,
                overflow = TextOverflow.Companion.Ellipsis,
                fontSize = 12.sp,
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .weight(1f),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.Companion.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "",
                    modifier = Modifier.Companion.size(15.dp)
                )

                Text(
                    formatDecimal(repo.starNumber),
                    fontSize = 12.sp,
                )

                Icon(
                    painterResource(R.drawable.ic_mdi_source_branch),
                    contentDescription = "",
                    modifier = Modifier.Companion.size(15.dp),
                )

                Text(
                    formatDecimal(repo.forkNumber),
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewRepositoryCard() {
    val context = LocalContext.current

    val repo = Repo(
        name = "android-repo",
        description = "Repository for homework on android studio.",
        starNumber = 31500,
        forkNumber = 13000,
        icon = R.drawable.ic_launcher_foreground,
    )
    val formatDecimal = { number: Int -> FormatDecimalUseCase(context)(number) }

    AndroidRepoTheme {
        Surface {
            RepoCard(
                repo = repo,
                formatDecimal = formatDecimal,
                modifier = Modifier.Companion.size(300.dp, 150.dp)
            )
        }
    }
}