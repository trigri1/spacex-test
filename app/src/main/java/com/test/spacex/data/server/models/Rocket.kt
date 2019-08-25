package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rocket(

    @SerializedName("rocket_id") val rocket_id: String,
    @SerializedName("rocket_name") val rocket_name: String,
    @SerializedName("rocket_type") val rocket_type: String,
    @SerializedName("first_stage") val first_stage: FirstStage,
    @SerializedName("second_stage") val second_stage: SecondStage,
    @SerializedName("fairings") val fairings: Fairings
) : Serializable