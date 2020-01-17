package io.kailuzhang.github.rcview

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatImageView

class RoundCornerImageView(
    context: Context,
    attrs: AttributeSet
) : AppCompatImageView(context, attrs) {

    @Px
    private var radius: Float = 0f
    private var radiusType: RadiusType = RadiusType.ALL

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerView)
        radius = ta.getDimension(R.styleable.RoundCornerView_cornerRadiusSize, 0f)
        val index = ta.getInt(R.styleable.RoundCornerView_cornerRadiusType, 0)
        radiusType = sRadiusType[index]
        setRadiusType(radiusType)
        ta.recycle()
    }

    fun setRadiusType(radiusType: RadiusType) {
        this.radiusType = radiusType
        outlineProvider = getRoundCornerOutlineWithPadding(radius, radiusType)
        clipToOutline = true
    }

    fun setRadius(@Px radius: Float) {
        this.radius = radius
        outlineProvider = getRoundCornerOutline(radius, radiusType)
        clipToOutline = true
    }
}