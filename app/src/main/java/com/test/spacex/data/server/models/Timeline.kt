package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Timeline(
    @SerializedName("webcast_liftoff") val webcast_liftoff: Double,
    @SerializedName("go_for_prop_loading") val go_for_prop_loading: Double,
    @SerializedName("rp1_loading") val rp1_loading: Double,
    @SerializedName("stage1_lox_loading") val stage1_lox_loading: Double,
    @SerializedName("stage2_lox_loading") val stage2_lox_loading: Double,
    @SerializedName("engine_chill") val engine_chill: Double,
    @SerializedName("prelaunch_checks") val prelaunch_checks: Double,
    @SerializedName("propellant_pressurization") val propellant_pressurization: Double,
    @SerializedName("go_for_launch") val go_for_launch: Double,
    @SerializedName("ignition") val ignition: Double,
    @SerializedName("liftoff") val liftoff: Double,
    @SerializedName("maxq") val maxq: Double,
    @SerializedName("meco") val meco: Double,
    @SerializedName("stage_sep") val stage_sep: Double,
    @SerializedName("second_stage_ignition") val second_stage_ignition: Double,
    @SerializedName("fairing_deploy") val fairing_deploy: Double,
    @SerializedName("first_stage_entry_burn") val first_stage_entry_burn: Double,
    @SerializedName("seco-1") val seco1: Double,
    @SerializedName("first_stage_landing") val first_stage_landing: Double,
    @SerializedName("second_stage_restart") val second_stage_restart: Double,
    @SerializedName("seco-2") val seco2: Double,
    @SerializedName("payload_deploy") val payload_deploy: Double
) : Serializable
