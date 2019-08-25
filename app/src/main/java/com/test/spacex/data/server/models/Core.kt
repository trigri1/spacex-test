package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Core(
    @SerializedName("core_serial") val core_serial: String,
    @SerializedName("flight") val flight: Double,
    @SerializedName("block") val block: Double,
    @SerializedName("gridfins") val gridfins: Boolean,
    @SerializedName("legs") val legs: Boolean,
    @SerializedName("reused") val reused: Boolean,
    @SerializedName("land_success") val land_success: Boolean,
    @SerializedName("landing_intent") val landing_intent: Boolean,
    @SerializedName("landing_type") val landing_type: String,
    @SerializedName("landing_vehicle") val landing_vehicle: String
) : Serializable
