package com.test.spacex.data.server.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Links(
    @SerializedName("mission_patch") val mission_patch: String,
    @SerializedName("mission_patch_small") val mission_patch_small: String,
    @SerializedName("reddit_campaign") val reddit_campaign: String,
    @SerializedName("reddit_launch") val reddit_launch: String,
    @SerializedName("reddit_recovery") val reddit_recovery: String,
    @SerializedName("reddit_media") val reddit_media: String,
    @SerializedName("presskit") val presskit: String,
    @SerializedName("article_link") val article_link: String,
    @SerializedName("wikipedia") val wikipedia: String,
    @SerializedName("video_link") val video_link: String,
    @SerializedName("youtube_id") val youtube_id: String,
    @SerializedName("flickr_images") val flickr_images: List<String>
) : Serializable