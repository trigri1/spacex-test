package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrbitParams(

    @SerializedName("reference_system") val reference_system: String,
    @SerializedName("regime") val regime: String,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("semi_major_axis_km") val semi_major_axis_km: Double,
    @SerializedName("eccentricity") val eccentricity: Double,
    @SerializedName("periapsis_km") val periapsis_km: Double,
    @SerializedName("apoapsis_km") val apoapsis_km: Double,
    @SerializedName("inclination_deg") val inclination_deg: Double,
    @SerializedName("period_min") val period_min: Double,
    @SerializedName("lifespan_years") val lifespan_years: Double,
    @SerializedName("epoch") val epoch: String,
    @SerializedName("mean_motion") val mean_motion: Double,
    @SerializedName("raan") val raan: Double,
    @SerializedName("arg_of_pericenter") val arg_of_pericenter: Double,
    @SerializedName("mean_anomaly") val mean_anomaly: Double
) : Serializable