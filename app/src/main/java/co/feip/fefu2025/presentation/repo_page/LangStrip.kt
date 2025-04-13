package co.feip.fefu2025.presentation.repo_page

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.Constants
import co.feip.fefu2025.domain.model.Lang
import co.feip.fefu2025.ui.theme.AndroidRepoTheme

@Composable
fun LangStrip(
    modifier: Modifier = Modifier,
    langs: Array<Lang>,
    stripWidth: Dp,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(stripWidth)
                .clip(RoundedCornerShape(stripWidth / 2))
        ) {
            val height = size.height
            val width = size.width
            var acc = 0f

            for (lang in langs) {
                drawLine(
                    start = Offset(x = width * acc / 100, y = height / 2),
                    end = Offset(x = width * (acc + lang.percentage) / 100, y = height / 2),
                    color = lang.color,
                    strokeWidth = stripWidth.toPx()
                )
                acc += lang.percentage
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun LanguageStripPreview() {
    val langs = arrayOf(
        Lang("C++", 85.0f, Color(Constants.languageColor.getValue("C++"))),
        Lang("Lua", 10f, Color(Constants.languageColor.getValue("Lua"))),
        Lang("CMake", 5f, Color(Constants.languageColor.getValue("CMake"))),
    )

    AndroidRepoTheme {
        Surface {
            LangStrip(
                modifier = Modifier
                    .size(400.dp, 200.dp)
                    .padding(10.dp),
                langs = langs,
                stripWidth = 10.dp,
            )
        }
    }
}