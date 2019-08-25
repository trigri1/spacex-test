package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FirstStage(@SerializedName("cores") val core: List<Core>) : Serializable
