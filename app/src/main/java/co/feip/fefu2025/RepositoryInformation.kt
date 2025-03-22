package co.feip.fefu2025

import android.icu.text.CompactDecimalFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.datetime.LocalDate
import kotlin.random.Random


@Composable
fun RepositoryInformation(
    name: String,
    description: String,
    forkNumber: Int,
    starNumber: Int,
    date: LocalDate,
    languages: Array<String>,
    icon: Int,
    modifier: Modifier
) {
    val context = LocalContext.current
    var isDescriptionExpanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painterResource(icon), contentDescription = "", modifier = Modifier.size(64.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
            Text(
                description,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        isDescriptionExpanded = !isDescriptionExpanded
                    }),
                maxLines = if (isDescriptionExpanded) Int.MAX_VALUE else 2,
                overflow = if (isDescriptionExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(R.drawable.mdisourcebranch),
                    contentDescription = "",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    "${
                        CompactDecimalFormat.getInstance(
                            context.resources.configuration.locales[0],
                            CompactDecimalFormat.CompactStyle.SHORT
                        ).format(forkNumber)
                    } ${if (starNumber == 1) "fork" else "forks"}", fontSize = 14.sp
                )
                Spacer(Modifier.width(10.dp))
                Icon(
                    painterResource(R.drawable.mdistar),
                    contentDescription = "",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    "${
                        CompactDecimalFormat.getInstance(
                            context.resources.configuration.locales[0],
                            CompactDecimalFormat.CompactStyle.SHORT
                        ).format(starNumber)
                    } ${if (starNumber == 1) "star" else "stars"}", fontSize = 14.sp
                )
            }
            Text("Created $date", fontSize = 12.sp, fontStyle = FontStyle.Italic)
            Text("Languages:", fontSize = 12.sp)
            AndroidView(factory = { context ->
                FlexBoxLayout(context).apply {
                    for (lang in languages) {
                        val lang = LanguageLabel(context).apply {
                            this.name = lang
                            this.color = Constants.getLanguageColor(lang)
                            this.percentage = Random.nextFloat() * 100
                        }
                        addView(lang)
                    }
                }
            })
        }
    }
}