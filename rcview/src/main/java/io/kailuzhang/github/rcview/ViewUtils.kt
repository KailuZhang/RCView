@file:JvmName("ViewUtils")

package io.kailuzhang.github.rcview

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.Px
import kotlin.math.ceil

val CIRCULAR_OUTLINE = object : ViewOutlineProvider() {
    override fun getOutline(view: View, outline: Outline) {
        outline.setOval(
            view.paddingLeft,
            view.paddingTop,
            view.width - view.paddingLeft,
            view.height - view.paddingTop
        )
    }
}

val sRadiusType = arrayOf(
    RadiusType.ALL,
    RadiusType.TOP,
    RadiusType.LEFT,
    RadiusType.BOTTOM,
    RadiusType.RIGHT
)

fun getRoundCornerOutline(
    @Px radius: Float,
    radiusType: RadiusType
): ViewOutlineProvider {
    val ceilRadius = ceil(radius).toInt()

    return object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            when (radiusType) {
                RadiusType.ALL -> {
                    outline.setRoundRect(
                        0,
                        0,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radius
                    )
                }
                RadiusType.TOP -> {
                    outline.setRoundRect(
                        0,
                        0,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop + ceilRadius,
                        radius
                    )
                }
                RadiusType.BOTTOM -> {
                    outline.setRoundRect(
                        0,
                        -ceilRadius,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radius
                    )
                }
                RadiusType.LEFT -> {
                    outline.setRoundRect(
                        0,
                        0,
                        view.width - view.paddingLeft + ceilRadius,
                        view.height - view.paddingTop,
                        radius
                    )
                }
                RadiusType.RIGHT -> {
                    outline.setRoundRect(
                        -ceilRadius,
                        0,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radius
                    )
                }
            }
        }
    }
}

fun getRoundCornerOutlineWithPadding(
    @Px radius: Float,
    radiusType: RadiusType
): ViewOutlineProvider {
    val ceilRadius = ceil(radius).toInt()
    return object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            when (radiusType) {
                RadiusType.ALL -> {
                    outline.setRoundRect(
                        view.paddingLeft,
                        view.paddingTop,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radius
                    )
                }
                RadiusType.TOP -> {
                    outline.setRoundRect(
                        view.paddingLeft,
                        view.paddingTop,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop + ceilRadius,
                        radius
                    )
                }
                RadiusType.BOTTOM -> {
                    outline.setRoundRect(
                        view.paddingLeft,
                        view.paddingTop - ceilRadius,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radius
                    )
                }
                RadiusType.LEFT -> {
                    outline.setRoundRect(
                        view.paddingLeft,
                        view.paddingTop,
                        view.width - view.paddingLeft + ceilRadius,
                        view.height - view.paddingTop,
                        radius
                    )
                }
                RadiusType.RIGHT -> {
                    outline.setRoundRect(
                        view.paddingLeft - ceilRadius,
                        view.paddingTop,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radius
                    )
                }
            }
        }
    }
}