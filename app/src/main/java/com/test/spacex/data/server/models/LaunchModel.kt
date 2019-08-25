package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LaunchModel(
    @SerializedName("flight_number") val flight_number: Double,
    @SerializedName("mission_name") val mission_name: String,
    @SerializedName("mission_id") val mission_id: List<String>,
    @SerializedName("launch_year") val launch_year: Double,
    @SerializedName("launch_date_unix") val launch_date_unix: Double,
    @SerializedName("launch_date_utc") val launch_date_utc: String,
    @SerializedName("launch_date_local") val launch_date_local: String,
    @SerializedName("is_tentative") val is_tentative: Boolean,
    @SerializedName("tentative_max_precision") val tentative_max_precision: String,
    @SerializedName("tbd") val tbd: Boolean,
    @SerializedName("launch_window") val launch_window: Double,
    @SerializedName("rocket") val rocket: Rocket,
    @SerializedName("ships") val ships: List<String>,
    @SerializedName("telemetry") val telemetry: Telemetry,
    @SerializedName("launch_site") val launch_site: LaunchSite,
    @SerializedName("launch_success") val launch_success: Boolean,
    @SerializedName("links") val links: Links,
    @SerializedName("details") val details: String,
    @SerializedName("upcoming") val upcoming: Boolean,
    @SerializedName("static_fire_date_utc") val static_fire_date_utc: String,
    @SerializedName("static_fire_date_unix") val static_fire_date_unix: Double,
    @SerializedName("timeline") val timeline: Timeline
) : Serializable
