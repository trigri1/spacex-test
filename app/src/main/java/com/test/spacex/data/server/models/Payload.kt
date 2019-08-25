package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Payload(

    @SerializedName("payload_id") val payload_id: String,
    @SerializedName("norad_id") val norad_id: List<Double>,
    @SerializedName("reused") val reused: Boolean,
    @SerializedName("customers") val customers: List<String>,
    @SerializedName("nationality") val nationality: String,
    @SerializedName("manufacturer") val manufacturer: String,
    @SerializedName("payload_type") val payload_type: String,
    @SerializedName("payload_mass_kg") val payload_mass_kg: Double,
    @SerializedName("payload_mass_lbs") val payload_mass_lbs: Double,
    @SerializedName("orbit") val orbit: String,
    @SerializedName("orbit_params") val orbit_params: OrbitParams
) : Serializable