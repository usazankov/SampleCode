package ru.sample.presentation.view.utils

import android.content.Context
import android.content.res.Resources
import android.os.Vibrator
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
    }

}