package com.nishimura.android.shopifyapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "product_query_result")
data class CollectEntry(
    @SerializedName("collection_id")
    val collectionId: Long,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("featured")
    val featured: Boolean,
    @PrimaryKey
    val id: Long,
    @SerializedName("position")
    val position: Int,
    @SerializedName("product_id")
    val productId: Long,
    @SerializedName("sort_value")
    val sortValue: String,
    @SerializedName("updated_at")
    val updatedAt: String
)