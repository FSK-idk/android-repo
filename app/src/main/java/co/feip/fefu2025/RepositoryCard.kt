package co.feip.fefu2025

import android.content.res.Configuration
import android.icu.text.CompactDecimalFormat
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
import co.feip.fefu2025.ui.theme.AndroidRepoTheme


class RepositoryCardData(
    var name: String,
    var description: String,
    var forkNumber: Int,
    var starNumber: Int,
    var icon: Int,
)


@Composable
fun RepositoryCard(
    data: RepositoryCardData,
    modifier: Modifier,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(data.icon),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    data.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }

            Text(
                data.description,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "",
                    modifier = Modifier.size(15.dp)
                )

                Text(
                    CompactDecimalFormat.getInstance(
                        context.resources.configuration.locales[0],
                        CompactDecimalFormat.CompactStyle.SHORT
                    ).format(data.starNumber),
                    fontSize = 12.sp,
                )

                Icon(
                    painterResource(R.drawable.ic_mdi_source_branch),
                    contentDescription = "",
                    modifier = Modifier.size(15.dp),
                )

                Text(
                    CompactDecimalFormat.getInstance(
                        context.resources.configuration.locales[0],
                        CompactDecimalFormat.CompactStyle.SHORT
                    ).format(data.forkNumber),
                    fontSize = 12.sp,
                )
            }
        }
    }
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewRepositoryCard() {
    val data = RepositoryCardData(
        name = "android-repo",
        description = "Repository for homework on android studio.",
        starNumber = 31500,
        forkNumber = 13000,
        icon = R.drawable.ic_launcher_foreground,
    )

    AndroidRepoTheme {
        Surface {
            RepositoryCard(
                data = data,
                modifier = Modifier.size(300.dp, 150.dp)
            )
        }
    }
}
