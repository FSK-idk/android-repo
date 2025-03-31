package co.feip.fefu2025

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import co.feip.fefu2025.databinding.LayoutLanguageLabelBinding


class LanguageLabelData(
    var name: String,
    var percentage: Float,
)


class LanguageLabel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding: LayoutLanguageLabelBinding =
        LayoutLanguageLabelBinding.inflate(LayoutInflater.from(context), this, true)

    private var _data: LanguageLabelData = LanguageLabelData("", 0f)
    var data: LanguageLabelData
        get() = _data
        set(data) {
            _data = data
            binding.name.text = data.name
            @SuppressLint("SetTextI18n")
            binding.percentage.text = "${"%.1f".format(data.percentage)}%"
            binding.circle.drawable.mutate().setTint(Constants.languageColor.getValue(data.name).toInt())
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.LanguageLabel, defStyleAttr, defStyleRes
        )
        val name = typedArray.getString(R.styleable.LanguageLabel_name)
        val percentage = typedArray.getFloat(R.styleable.LanguageLabel_percentage, 0.0f)
        data = LanguageLabelData(name ?: "", percentage)
    }
}
