package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SecondStage(
    @SerializedName("block") val block: Double,
    @SerializedName("payloads") val payloads: List<Payload>
) : Serializable