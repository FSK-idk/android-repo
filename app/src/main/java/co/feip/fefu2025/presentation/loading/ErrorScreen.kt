package co.feip.fefu2025.presentation.loading

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.ui.theme.AndroidRepoTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRetryClick: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        ),
    ) {
        Text(text = stringResource(R.string.Error_occurred))

        if (onRetryClick != null) {
            Button(onClick = onRetryClick) {
                Text(text = stringResource(R.string.Retry))
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ErrorScreenPreview() {
    AndroidRepoTheme {
        Surface {
            ErrorScreen(
                onRetryClick = {},
            )
        }
    }
}