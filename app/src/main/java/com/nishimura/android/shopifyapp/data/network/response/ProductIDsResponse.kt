package com.nishimura.android.shopifyapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.nishimura.android.shopifyapp.data.db.entity.CollectEntry

data class ProductIDsResponse(
    @SerializedName("collects")
    val collects: List<CollectEntry>
)