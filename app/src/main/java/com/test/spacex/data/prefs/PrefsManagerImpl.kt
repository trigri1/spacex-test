package com.test.spacex.data.prefs

import android.content.SharedPreferences
import javax.inject.Inject

class PrefsManagerImpl @Inject constructor(val sharedPreferences: SharedPreferences) :
    PrefsManager {
}