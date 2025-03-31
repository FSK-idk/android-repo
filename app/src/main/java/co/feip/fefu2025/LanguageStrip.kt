package co.feip.fefu2025

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
import co.feip.fefu2025.ui.theme.AndroidRepoTheme


@Composable
fun LanguageStrip(
    languageLabelsData: Array<LanguageLabelData>,
    stripWidth: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            Modifier
                .fillMaxWidth()
                .height(stripWidth)
                .clip(RoundedCornerShape(stripWidth / 2))
        ) {
            val height = size.height
            val width = size.width
            var acc = 0f

            for (data in languageLabelsData) {
                drawLine(
                    start = Offset(x = width * acc / 100, y = height / 2),
                    end = Offset(x = width * (acc + data.percentage) / 100, y = height / 2),
                    color = Color(Constants.languageColor.getValue(data.name)),
                    strokeWidth = stripWidth.toPx()
                )
                acc += data.percentage
            }
        }
    }
}


@Composable
@Preview
fun LanguageStripPreview() {
    val langData = arrayOf(
        LanguageLabelData("C++", 85.0f),
        LanguageLabelData("Lua", 10f),
        LanguageLabelData("CMake", 5f),
    )

    AndroidRepoTheme {
        Surface {
            LanguageStrip(
                languageLabelsData = langData,
                stripWidth = 10.dp,
                modifier = Modifier
                    .size(400.dp, 200.dp)
                    .padding(10.dp)
            )
        }
    }
}
