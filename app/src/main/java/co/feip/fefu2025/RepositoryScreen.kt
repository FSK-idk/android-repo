package co.feip.fefu2025

import android.content.res.Configuration
import android.icu.text.CompactDecimalFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import co.feip.fefu2025.ui.theme.AndroidRepoTheme
import kotlinx.datetime.LocalDate


class RepositoryScreenData(
    var name: String,
    var description: String,
    var forkNumber: Int,
    var starNumber: Int,
    var date: LocalDate,
    var languageLabelsData: Array<LanguageLabelData>,
    var icon: Int,
)


@Composable
fun RepositoryScreen(
    data: RepositoryScreenData,
    modifier: Modifier
) {
    val context = LocalContext.current
    var isDescriptionExpanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(data.icon),
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                )

                Text(
                    data.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Text(
                "About",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        isDescriptionExpanded = !isDescriptionExpanded
                    })
            )

            Text(
                data.description,
                maxLines = if (isDescriptionExpanded) Int.MAX_VALUE else 2,
                overflow = if (isDescriptionExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        isDescriptionExpanded = !isDescriptionExpanded
                    })
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    "${
                        CompactDecimalFormat.getInstance(
                            context.resources.configuration.locales[0],
                            CompactDecimalFormat.CompactStyle.SHORT
                        ).format(data.starNumber)
                    } ${if (data.starNumber == 1) "star" else "stars"}",
                    fontSize = 14.sp,
                )

                Icon(
                    painterResource(R.drawable.ic_mdi_source_branch),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    "${
                        CompactDecimalFormat.getInstance(
                            context.resources.configuration.locales[0],
                            CompactDecimalFormat.CompactStyle.SHORT
                        ).format(data.forkNumber)
                    } ${if (data.starNumber == 1) "fork" else "forks"}",
                    fontSize = 14.sp,
                )
            }

            Text(
                "Created ${data.date}",
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic,
            )

            HorizontalDivider(thickness = 2.dp)

            Text(
                "Languages",
                fontWeight = FontWeight.Bold,
            )

            LanguageStrip(
                languageLabelsData = data.languageLabelsData,
                stripWidth = 6.dp,
            )

            AndroidView(factory = { context ->
                FlexBoxLayout(context).apply {
                    for (data in data.languageLabelsData) {
                        val lang = LanguageLabel(context).apply {
                            this.data = data
                        }
                        addView(lang)
                    }
                }
            })

            HorizontalDivider(thickness = 2.dp)
        }
    }
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewRepositoryScreen() {
    val data = RepositoryScreenData(
        name = "android-repo",
        description = "Repository for homework on android studio.",
        forkNumber = 13000,
        starNumber = 31500,
        date = LocalDate(2025, 3, 3),
        languageLabelsData = arrayOf(
            LanguageLabelData("Kotlin", 85.0f),
            LanguageLabelData("Python", 10f),
            LanguageLabelData("C++", 3f),
            LanguageLabelData("Lua", 2f),
        ),
        icon = R.drawable.ic_launcher_foreground,
    )

    AndroidRepoTheme {
        Scaffold(
            topBar = { ButtonTopBar("android-repo") }
        ) { innerPadding ->
            RepositoryScreen(
                data = data,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}
