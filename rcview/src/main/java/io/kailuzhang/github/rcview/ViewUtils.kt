@file:JvmName("ViewUtils")

package io.kailuzhang.github.rcview

import android.content.Context
import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
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
    RadiusType.BOTTOM,
    RadiusType.LEFT,
    RadiusType.RIGHT
)

fun getRoundCornerOutline(
    context: Context,
    radius: Float,
    radiusType: RadiusType
): ViewOutlineProvider {
    val dm = context.resources.displayMetrics
    val density = dm.density
    val radiusPx = density * radius
    val ceilRadiusPx = ceil(radiusPx).toInt()

    return object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            when (radiusType) {
                RadiusType.ALL -> {
                    outline.setRoundRect(
                        0,
                        0,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radiusPx
                    )
                }
                RadiusType.TOP -> {
                    outline.setRoundRect(
                        0,
                        0,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop + ceilRadiusPx,
                        radiusPx
                    )
                }
                RadiusType.BOTTOM -> {
                    outline.setRoundRect(
                        0,
                        -ceilRadiusPx,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radiusPx
                    )
                }
                RadiusType.LEFT -> {
                    outline.setRoundRect(
                        0,
                        0,
                        view.width - view.paddingLeft + ceilRadiusPx,
                        view.height - view.paddingTop,
                        radiusPx
                    )
                }
                RadiusType.RIGHT -> {
                    outline.setRoundRect(
                        -ceilRadiusPx,
                        0,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radiusPx
                    )
                }
            }
        }
    }
}

fun getRoundCornerOutlineWithPadding(
    context: Context,
    radius: Float,
    radiusType: RadiusType
): ViewOutlineProvider {
    val dm = context.resources.displayMetrics
    val density = dm.density
    val radiusPx = density * radius
    val ceilRadiusPx = ceil(radiusPx).toInt()

    return object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            when (radiusType) {
                RadiusType.ALL -> {
                    outline.setRoundRect(
                        view.paddingLeft,
                        view.paddingTop,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radiusPx
                    )
                }
                RadiusType.TOP -> {
                    outline.setRoundRect(
                        view.paddingLeft,
                        view.paddingTop,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop + ceilRadiusPx,
                        radiusPx
                    )
                }
                RadiusType.BOTTOM -> {
                    outline.setRoundRect(
                        view.paddingLeft,
                        view.paddingTop - ceilRadiusPx,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radiusPx
                    )
                }
                RadiusType.LEFT -> {
                    outline.setRoundRect(
                        view.paddingLeft,
                        view.paddingTop,
                        view.width - view.paddingLeft + ceilRadiusPx,
                        view.height - view.paddingTop,
                        radiusPx
                    )
                }
                RadiusType.RIGHT -> {
                    outline.setRoundRect(
                        view.paddingLeft - ceilRadiusPx,
                        view.paddingTop,
                        view.width - view.paddingLeft,
                        view.height - view.paddingTop,
                        radiusPx
                    )
                }
            }
        }
    }
}