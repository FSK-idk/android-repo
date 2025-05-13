package co.feip.fefu2025.presentation.repo_page

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.ui.theme.AndroidRepoTheme

@Composable
fun StarButton(
    starred: Boolean,
    onClick: () -> Unit
) {

    FloatingActionButton(
        onClick = onClick,
    ) {
        if (starred) {
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = Icons.Filled.Star,
                contentDescription = null,
            )
        } else {
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = Icons.Filled.Star,
                tint = MaterialTheme.colorScheme.inversePrimary,
                contentDescription = null,
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun StarButtonPreview() {
    AndroidRepoTheme {
        Surface {
            StarButton(
                starred = true,
                onClick = {},
            )
        }
    }
}