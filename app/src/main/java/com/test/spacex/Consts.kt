package com.test.spacex

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object Consts {
    const val PREF_NAME: String = "spacex_prefs"
    const val BASE_URL: String = "https://api.spacexdata.com"

    const val HTTP_CACHE_DIRECTORY = "cache-dir"
    const val HTTP_CACHE_MAX_AGE = 60 * 60 * 24 * 1
    const val HTTP_CACHE_STALE_MAX_AGE = 60 * 60 * 24 * 2


    fun getFormattedDate(context: Context, date: String): String {
        val local = context.resources.configuration.locale
        val oldFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", local)
        val result = oldFormat.parse(date)
        val newFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", local)
        newFormat.timeZone = TimeZone.getTimeZone("GMT")

        return newFormat.format(result)
    }


}