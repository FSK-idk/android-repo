package co.feip.fefu2025

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import co.feip.fefu2025.databinding.LanguageLabelBinding

class LanguageLabel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding: LanguageLabelBinding =
        LanguageLabelBinding.inflate(LayoutInflater.from(context), this, true)
    private var _name: String = ""
    var name: String
        get() = binding.name.text.toString()
        set(name) {
            _name = name
            binding.name.text = _name
        }

    private var _color: Int = 0
    var color: Int
        get() = _color
        set(color) {
            _color = color
            binding.circle.drawable.mutate().setTint(color)
        }

    private var _percentage: Float = 0.0f
    var percentage: Float
        get() = _percentage
        set(percentage) {
            _percentage = percentage
            @SuppressLint("SetTextI18n")
            binding.percentage.text = "${"%.1f".format(percentage)}%"
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.LanguageLabel, defStyleAttr, defStyleRes
        )
        val name = typedArray.getString(R.styleable.LanguageLabel_name)
        val percentage = typedArray.getFloat(R.styleable.LanguageLabel_percentage, 0.0f)
        val color = typedArray.getColor(R.styleable.LanguageLabel_color, 0)
        this.name = name ?: ""
        this.color = color
        this.percentage = percentage
    }
}
