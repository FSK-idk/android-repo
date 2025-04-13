package co.feip.fefu2025.presentation.repo_page

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import co.feip.fefu2025.Constants
import co.feip.fefu2025.presentation.shared.FlexBoxLayout
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.Lang
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.ui.theme.AndroidRepoTheme
import kotlinx.datetime.LocalDate

@Composable
fun RepoPageScreen(
    modifier: Modifier = Modifier,
    isDescriptionExpanded: Boolean,
    repoName: String,
    repoDescription: String,
    repoStarNumber: Int,
    repoForkNumber: Int,
    repoCreationDate: LocalDate,
    repoLangs: Array<Lang>,
    repoIcon: Int,
    turnDescription: () -> Unit,
    formatDecimal: (Int) -> String,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.Companion
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.Companion.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.Companion.size(60.dp),
                    painter = painterResource(repoIcon),
                    contentDescription = null,
                )

                Text(
                    text = repoName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Companion.Bold,
                )
            }

            Text(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .clickable(onClick = turnDescription),
                text = stringResource(R.string.About),
                fontWeight = FontWeight.Companion.Bold,
            )

            Text(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .clickable(onClick = turnDescription),
                text = repoDescription,
                maxLines = if (isDescriptionExpanded) Int.MAX_VALUE else 2,
                overflow = if (isDescriptionExpanded) TextOverflow.Companion.Visible else TextOverflow.Companion.Ellipsis,
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.Companion.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.Companion.size(20.dp),
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                )

                Text(
                    text = "${formatDecimal(repoStarNumber)} ${pluralStringResource(R.plurals.star, repoStarNumber)}",
                    fontSize = 14.sp,
                )

                Icon(
                    modifier = Modifier.Companion.size(20.dp),
                    painter = painterResource(R.drawable.ic_mdi_source_branch),
                    contentDescription = null,
                )

                Text(
                    text = "${formatDecimal(repoForkNumber)} ${pluralStringResource(R.plurals.star, repoForkNumber)}",
                    fontSize = 14.sp,
                )
            }

            Text(
                text = stringResource(R.string.Created, repoCreationDate),
                fontSize = 12.sp,
                fontStyle = FontStyle.Companion.Italic,
            )

            HorizontalDivider(thickness = 2.dp)

            Text(
                text = stringResource(R.string.Languages),
                fontWeight = FontWeight.Companion.Bold,
            )

            LangStrip(
                langs = repoLangs,
                stripWidth = 6.dp,
            )

            AndroidView(
                factory = { context ->
                    FlexBoxLayout(context).apply {
                        repoLangs.forEach { lang ->
                            val langLabel = LangLabel(context).apply {
                                this.lang = lang
                            }
                            addView(langLabel)
                        }
                    }
                },
                update = {
                    it.apply {
                        it.removeAllViews()
                        repoLangs.forEach { lang ->
                            val langLabel = LangLabel(it.context).apply {
                                this.lang = lang
                            }
                            it.addView(langLabel)
                        }
                    }
                }
            )

            HorizontalDivider(thickness = 2.dp)
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun RepositoryScreenPreview() {
    val isDescriptionExpanded = false
    val repoName = "android-repo"
    val repoDescription = "Repository for homework on android studio."
    val repoStarNumber = 13000
    val repoForkNumber = 31500
    val repoCreationDate = LocalDate(2025, 3, 3)
    val repoLangs = arrayOf(
        Lang("Kotlin", 85f, Color(Constants.languageColor.getValue("Kotlin"))),
        Lang("Python", 10f, Color(Constants.languageColor.getValue("Python"))),
        Lang("C++", 3f, Color(Constants.languageColor.getValue("C++"))),
        Lang("Lua", 2f, Color(Constants.languageColor.getValue("Lua"))),
    )
    val repoIcon = R.drawable.ic_launcher_foreground

    AndroidRepoTheme {
        Surface {
            RepoPageScreen(
                isDescriptionExpanded = isDescriptionExpanded,
                repoName = repoName,
                repoDescription = repoDescription,
                repoStarNumber = repoStarNumber,
                repoForkNumber = repoForkNumber,
                repoCreationDate = repoCreationDate,
                repoLangs = repoLangs,
                repoIcon = repoIcon,
                turnDescription = {},
                formatDecimal = FormatDecimalUseCase(LocalContext.current)::invoke,
            )
        }
    }
}