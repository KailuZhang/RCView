package io.kailuzhang.github.rcview

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(
    "app:cornerRadiusSize",
    "app:cornerRadiusType",
    "app:usePadding",
    requireAll = false
)
fun setRoundCornerOutline(
    view: View,
    cornerRadiusSize: Float,
    cornerRadiusType: Int = 0,
    usePadding: Boolean = false
) {
    view.outlineProvider = if (usePadding) {
        getRoundCornerOutlineWithPadding(cornerRadiusSize, sRadiusType[cornerRadiusType])
    } else {
        getRoundCornerOutline(cornerRadiusSize, sRadiusType[cornerRadiusType])
    }
    view.clipToOutline = true
}