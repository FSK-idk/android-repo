package co.feip.fefu2025

import android.content.res.Configuration
import android.icu.text.CompactDecimalFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.ui.theme.AndroidRepoTheme


@Composable
fun RepositoryCard(
    name: String,
    description: String,
    forkNumber: Int,
    starNumber: Int,
    icon: Int,
    modifier: Modifier,
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painterResource(icon), contentDescription = "", modifier = Modifier.size(64.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(name, fontWeight = FontWeight.Bold)
            }

            Text(
                description,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                overflow = TextOverflow.Ellipsis,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(R.drawable.mdistar),
                    contentDescription = "",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    CompactDecimalFormat.getInstance(
                        context.resources.configuration.locales[0],
                        CompactDecimalFormat.CompactStyle.SHORT
                    ).format(starNumber)
                )

                Spacer(Modifier.width(10.dp))

                Icon(
                    painterResource(R.drawable.mdisourcebranch),
                    contentDescription = "",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    CompactDecimalFormat.getInstance(
                        context.resources.configuration.locales[0],
                        CompactDecimalFormat.CompactStyle.SHORT
                    ).format(forkNumber)
                )
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewRepositoryCard() {
    AndroidRepoTheme {
        RepositoryCard(
            name = "android-repo",
            description = "Repository for homework on android studio.",
            starNumber = 31500,
            forkNumber = 13000,
            icon = R.drawable.ic_launcher_foreground,
            modifier = Modifier.size(400.dp, 200.dp)
        )
    }
}
