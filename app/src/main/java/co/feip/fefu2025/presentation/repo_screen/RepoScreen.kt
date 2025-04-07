package co.feip.fefu2025.presentation.repo_screen

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
fun RepoScreenRoot(
    viewModel: RepoScreenViewModel,
    modifier: Modifier = Modifier,
) {
    val isDescriptionExpanded = viewModel.isDescriptionExpanded.collectAsState()
    val repoName = viewModel.repoName.collectAsState()
    val repoDescription = viewModel.repoDescription.collectAsState()
    val repoStarNumber = viewModel.repoStarNumber.collectAsState()
    val repoForkNumber = viewModel.repoForkNumber.collectAsState()
    val repoCreationDate = viewModel.repoCreationDate.collectAsState()
    val repoLangs = viewModel.repoLangs.collectAsState()
    val repoIcon = viewModel.repoIcon.collectAsState()

    Scaffold(
        topBar = {
            ButtonTopBar(
                title = "android-repo",
                onBackClick = viewModel::onBackClick
            )
        },
        modifier = modifier
    ) { innerPadding ->
        RepoScreen(
            isDescriptionExpanded = isDescriptionExpanded.value,
            repoName = repoName.value,
            repoDescription = repoDescription.value,
            repoStarNumber = repoStarNumber.value,
            repoForkNumber = repoForkNumber.value,
            repoCreationDate = repoCreationDate.value,
            repoLangs = repoLangs.value,
            repoIcon = repoIcon.value,
            turnDescription = viewModel::turnDescription,
            formatDecimal = viewModel::formatDecimal,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
fun RepoScreen(
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
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.Companion
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.Companion.CenterVertically,
            ) {
                Icon(
                    painterResource(repoIcon),
                    contentDescription = "",
                    modifier = Modifier.Companion.size(60.dp)
                )

                Text(
                    repoName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Companion.Bold,
                )
            }

            Text(
                "About",
                fontWeight = FontWeight.Companion.Bold,
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .clickable(onClick = turnDescription)
            )

            Text(
                repoDescription,
                maxLines = if (isDescriptionExpanded) Int.MAX_VALUE else 2,
                overflow = if (isDescriptionExpanded) TextOverflow.Companion.Visible else TextOverflow.Companion.Ellipsis,
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .clickable(onClick = turnDescription)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.Companion.CenterVertically,
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "",
                    modifier = Modifier.Companion.size(20.dp)
                )

                Text(
                    "${formatDecimal(repoStarNumber)} ${if (repoStarNumber == 1) "star" else "stars"}",
                    fontSize = 14.sp,
                )

                Icon(
                    painterResource(R.drawable.ic_mdi_source_branch),
                    contentDescription = "",
                    modifier = Modifier.Companion.size(20.dp)
                )

                Text(
                    "${formatDecimal(repoForkNumber)} ${if (repoForkNumber == 1) "fork" else "forks"}",
                    fontSize = 14.sp,
                )
            }

            Text(
                "Created $repoCreationDate",
                fontSize = 12.sp,
                fontStyle = FontStyle.Companion.Italic,
            )

            HorizontalDivider(thickness = 2.dp)

            Text(
                "Languages",
                fontWeight = FontWeight.Companion.Bold,
            )

            LangStrip(
                langs = repoLangs,
                stripWidth = 6.dp,
            )

            AndroidView(factory = { context ->
                FlexBoxLayout(context).apply {
                    for (lang in repoLangs) {
                        val langLabel = LangLabel(context).apply {
                            this.lang = lang
                        }
                        addView(langLabel)
                    }
                }
            })

            HorizontalDivider(thickness = 2.dp)
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun RepositoryScreenPreview() {
    val context = LocalContext.current

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
    val turnDescription = {}
    val formatDecimal = { number: Int -> FormatDecimalUseCase(context)(number) }
    val onBackClick = {}

    AndroidRepoTheme {
        Scaffold(
            topBar = {
                ButtonTopBar(
                    title = "android-repo",
                    onBackClick = onBackClick,
                )
            },
        ) { innerPadding ->
            RepoScreen(
                isDescriptionExpanded = isDescriptionExpanded,
                repoName = repoName,
                repoDescription = repoDescription,
                repoStarNumber = repoStarNumber,
                repoForkNumber = repoForkNumber,
                repoCreationDate = repoCreationDate,
                repoLangs = repoLangs,
                repoIcon = repoIcon,
                turnDescription = turnDescription,
                formatDecimal = formatDecimal,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}