package io.kailuzhang.github.rcview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CircularImageView(
    context: Context,
    attrs: AttributeSet
) : AppCompatImageView(context, attrs) {

    init {
        outlineProvider = CIRCULAR_OUTLINE
        clipToOutline = true
    }
}