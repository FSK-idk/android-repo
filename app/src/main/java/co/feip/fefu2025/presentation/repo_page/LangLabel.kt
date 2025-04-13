package co.feip.fefu2025.presentation.repo_page

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.compose.ui.graphics.Color
import co.feip.fefu2025.Constants
import co.feip.fefu2025.R
import co.feip.fefu2025.databinding.LayoutLanguageLabelBinding
import co.feip.fefu2025.domain.model.Lang

class LangLabel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding: LayoutLanguageLabelBinding =
        LayoutLanguageLabelBinding.inflate(LayoutInflater.from(context), this, true)

    private var _lang: Lang = Lang("", 0f, Color(0))
    var lang: Lang
        get() = _lang
        set(data) {
            _lang = data
            binding.name.text = data.name
            @SuppressLint("SetTextI18n")
            binding.percentage.text = "${"%.1f".format(data.percentage)}%"
            binding.circle.drawable.mutate()
                .setTint(Constants.Companion.languageColor.getValue(data.name).toInt())
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.LanguageLabel, defStyleAttr, defStyleRes
        )
        val name = typedArray.getString(R.styleable.LanguageLabel_name)
        val percentage = typedArray.getFloat(R.styleable.LanguageLabel_percentage, 0.0f)
        lang = Lang(name ?: "", percentage, Color(0))
    }
}