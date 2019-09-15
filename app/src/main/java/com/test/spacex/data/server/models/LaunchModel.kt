package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LaunchModel(
    @SerializedName("flight_number") val flightNumber: Double?,
    @SerializedName("mission_name") val missionName: String?,
    @SerializedName("mission_id") val missionId: List<String>?,
    @SerializedName("launch_year") val launchYear: Double?,
    @SerializedName("launch_date_unix") val launchDateUnix: Double?,
    @SerializedName("launch_date_utc") val launchDateUtc: String?,
    @SerializedName("launch_date_local") val launchDateLocal: String?,
    @SerializedName("is_tentative") val isTentative: Boolean?,
    @SerializedName("tentative_max_precision") val tentativeMaxPrecision: String?,
    @SerializedName("tbd") val tbd: Boolean?,
    @SerializedName("launch_window") val launchWindow: Double?,
    @SerializedName("ships") val ships: List<String?>,
    @SerializedName("launch_success") val launchSuccess: Boolean?,
    @SerializedName("details") val details: String?,
    @SerializedName("upcoming") val upcoming: Boolean?,
    @SerializedName("static_fire_date_utc") val staticFireDateUtc: String?,
    @SerializedName("static_fire_date_unix") val staticFireDateUnix: Double?
) : Serializable
