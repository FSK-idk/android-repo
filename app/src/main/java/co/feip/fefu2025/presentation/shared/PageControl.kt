package co.feip.fefu2025.presentation.shared

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.FirstPage
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.ui.theme.AndroidRepoTheme

@Composable
fun PageControl(
    modifier: Modifier = Modifier,
    pageNumber: Int,
    onTopClick: () -> Unit,
    onFirstPageClick: () -> Unit,
    onPrevPageClick: () -> Unit,
    onNextPageClick: () -> Unit,
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterHorizontally,
        ),
    ) {
        Button(
            onClick = onFirstPageClick
        ) {
            Icon(
                modifier = Modifier.Companion.size(20.dp),
                imageVector = Icons.Outlined.FirstPage,
                contentDescription = null,
            )
        }

        Button(
            onClick = onPrevPageClick
        ) {
            Icon(
                modifier = Modifier.Companion.size(20.dp),
                imageVector = Icons.Outlined.ChevronLeft,
                contentDescription = null,
            )
        }

        Button(
            onClick = onTopClick,
        ) {
            Text(text = "$pageNumber")
        }

        Button(
            onClick = onNextPageClick,
        ) {
            Icon(
                modifier = Modifier.Companion.size(20.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun RepoListScreenPreview() {
    AndroidRepoTheme {
        Surface {
            PageControl(
                pageNumber = 24,
                onTopClick = {},
                onFirstPageClick = {},
                onPrevPageClick = {},
                onNextPageClick = {},
            )
        }
    }
}