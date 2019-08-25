package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Telemetry(
    @SerializedName("flight_club") val flight_club: String
) : Serializable
