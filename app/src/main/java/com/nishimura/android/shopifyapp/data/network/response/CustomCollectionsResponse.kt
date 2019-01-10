package com.nishimura.android.shopifyapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.nishimura.android.shopifyapp.data.db.entity.CustomCollectionEntry

data class CustomCollectionsResponse(
    @SerializedName("custom_collections")
    val customCollectionEntries: List<CustomCollectionEntry>
)