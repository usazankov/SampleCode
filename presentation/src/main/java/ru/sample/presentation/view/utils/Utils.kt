package ru.sample.presentation.view.utils

import android.content.Context
import android.content.res.Resources
import android.os.Vibrator
import android.view.ViewConfiguration
import androidx.annotation.StringRes
import ru.sample.presentation.AndroidApplication

class Utils {
    companion object{
        fun getString(@StringRes resId: Int): String {
            return AndroidApplication.application.getString(resId)
        }

        fun dpToPx(dp: Int): Int {
            return (dp * Resources.getSystem().displayMetrics.density).toInt()
        }

        fun pxToDp(px: Float): Float {
            return px / Resources.getSystem().displayMetrics.density
        }
        fun getStatusBarHeight(): Int {
            var result = 0
            val resourceId = AndroidApplication.application.getResources()
                .getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result =
                    AndroidApplication.application.getResources().getDimensionPixelSize(resourceId)
            }
            return result
        }

        fun getNavigationBarHeight(): Int {
            val hasMenuKey =
                ViewConfiguration.get(AndroidApplication.application).hasPermanentMenuKey()
            val resourceId = AndroidApplication.application.getResources()
                .getIdentifier("navigation_bar_height", "dimen", "android")
            return if (resourceId > 0 && !hasMenuKey) {
                AndroidApplication.application.getResources().getDimensionPixelSize(resourceId)
            } else 0
        }
    }

}