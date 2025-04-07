package co.feip.fefu2025.presentation.repo_screen

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.feip.fefu2025.ui.theme.AndroidRepoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonTopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(title)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        modifier = modifier,
    )
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun ButtonTopBarPreview() {
    val title = "Some title"
    val onBackClick = {}

    AndroidRepoTheme {
        Surface {
            ButtonTopBar(
                title = title,
                onBackClick = onBackClick,
            )
        }
    }
}