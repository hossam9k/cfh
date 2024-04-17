package com.example.cfh.core.views

import androidx.annotation.StringRes
import com.example.cfh.R


sealed class ViewStatus {
    object Inital : ViewStatus()
    object Loading : ViewStatus()

    object Success : ViewStatus()

    class Error(
        val message: String,
        @StringRes val buttonText: Int = R.string.retry,
        val action: (() -> Unit)? = null
    ) : ViewStatus()
}
