package com.yvkalume.ekazi.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.yvkalume.ekazi.R

fun getJSONFile(context: Context, file: String): String {
    return context.assets.open(file).bufferedReader().use { it.readText() }
}

fun String?.toDrawable(context: Context): Int {
    return if (this != null) {
        context.resources.getIdentifier(this, "drawable", context.packageName)
    } else {
        R.drawable.logo_no_company
    }
}

fun String.openInBrowser(context: Context) {
    var url = this
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        url = "http://$url";
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    } else {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }
}