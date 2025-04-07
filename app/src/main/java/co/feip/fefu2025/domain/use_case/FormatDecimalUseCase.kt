package co.feip.fefu2025.domain.use_case

import android.content.Context
import android.icu.text.CompactDecimalFormat

class FormatDecimalUseCase(
    private val context: Context
) {
    operator fun invoke(number: Int): String {
        return CompactDecimalFormat.getInstance(
            context.resources.configuration.locales[0],
            CompactDecimalFormat.CompactStyle.SHORT
        ).format(number)
    }
}