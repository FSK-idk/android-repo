package co.feip.fefu2025

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import kotlin.math.max


class FlexBoxLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val availableWidth =
            MeasureSpec.getSize(widthMeasureSpec) - marginLeft - marginRight - paddingLeft - paddingRight
        var currentWidth = marginLeft + paddingLeft
        var currentHeight = marginTop + paddingTop
        var maxWidth = 0
        var currentMaxHeight = 0
        var childState = 0


        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.isGone) continue
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            if (currentWidth + child.measuredWidth > availableWidth) {
                maxWidth = max(maxWidth, currentWidth)
                currentWidth = marginLeft + paddingLeft
                currentHeight += currentMaxHeight
                currentMaxHeight = 0
            }
            currentWidth += child.measuredWidth
            currentMaxHeight = max(currentMaxHeight, child.measuredHeight)
            childState = combineMeasuredStates(childState, child.measuredState)
        }
        maxWidth = max(maxWidth, currentWidth)
        currentHeight += currentMaxHeight + marginBottom + paddingBottom

        maxWidth = max(maxWidth, suggestedMinimumWidth)
        currentHeight = max(currentHeight, suggestedMinimumHeight)

        setMeasuredDimension(
            resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
            resolveSizeAndState(
                currentHeight,
                heightMeasureSpec,
                childState shl MEASURED_HEIGHT_STATE_SHIFT
            )
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val availableWidth = measuredWidth - marginLeft - marginRight - paddingLeft - paddingRight
        var currentWidth = marginLeft + paddingLeft
        var currentHeight = marginTop + paddingTop
        var currentMaxHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.isGone) continue
            if (currentWidth + child.measuredWidth > availableWidth) {
                currentWidth = marginLeft + paddingLeft
                currentHeight += currentMaxHeight
                currentMaxHeight = 0
            }
            child.layout(
                currentWidth,
                currentHeight,
                currentWidth + child.measuredWidth,
                currentHeight + child.measuredHeight
            )
            currentWidth += child.measuredWidth
            currentMaxHeight = max(currentMaxHeight, child.measuredHeight)
        }
    }
}
