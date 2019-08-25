package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Fairings(
    @SerializedName("reused") val reused: Boolean,
    @SerializedName("recovery_attempt") val recovery_attempt: Boolean,
    @SerializedName("recovered") val recovered: Boolean,
    @SerializedName("ship") val ship: String
) : Serializable
