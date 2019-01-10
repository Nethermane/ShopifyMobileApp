package com.nishimura.android.shopifyapp.data.db.entity

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("alt")
    val alt: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("src")
    val src: String,
    @SerializedName("width")
    val width: Int
)