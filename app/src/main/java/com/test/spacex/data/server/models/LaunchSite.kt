package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LaunchSite(
    @SerializedName("site_id") val site_id: String,
    @SerializedName("site_name") val site_name: String,
    @SerializedName("site_name_long") val site_name_long: String
) : Serializable