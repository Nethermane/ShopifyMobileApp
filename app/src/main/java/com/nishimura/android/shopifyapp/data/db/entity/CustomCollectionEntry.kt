package com.nishimura.android.shopifyapp.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "custom_collection")
data class CustomCollectionEntry(
    @SerializedName("admin_graphql_api_id")
    val adminGraphqlApiId: String,
    @SerializedName("body_html")
    val bodyHtml: String,
    @SerializedName("handle")
    val handle: String,
    @PrimaryKey
    val id: Long,
    @Embedded(prefix = "image_")
    val image: Image,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("published_scope")
    val publishedScope: String,
    @SerializedName("sort_order")
    val sortOrder: String,
    @SerializedName("template_suffix")
    val templateSuffix: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String
)