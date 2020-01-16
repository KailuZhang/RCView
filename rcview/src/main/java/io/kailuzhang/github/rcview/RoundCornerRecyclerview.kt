package io.kailuzhang.github.rcview

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class RoundCornerRecyclerview(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {

    @Px
    private var radius: Float = 0f
    private var radiusType: RadiusType = RadiusType.ALL

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerView)
        radius = ta.getDimension(R.styleable.RoundCornerView_radius, 0f)
        val index = ta.getInt(R.styleable.RoundCornerView_radiusType, 0)
        radiusType = sRadiusType[index]
        setRadiusType(radiusType)
        ta.recycle()
    }

    fun setRadiusType(radiusType: RadiusType) {
        this.radiusType = radiusType
        outlineProvider = getRoundCornerOutline(context, radius, radiusType)
        clipToOutline = true
    }

    fun setRadius(@Px radius: Float) {
        this.radius = radius
        outlineProvider =
            getRoundCornerOutline(context, radius, radiusType)
        clipToOutline = true
    }
}