package com.nishimura.android.shopifyapp.data.db.entity.entry

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nishimura.android.shopifyapp.data.db.entity.ProductImage
import com.nishimura.android.shopifyapp.data.db.entity.Variant

@Entity(tableName = "products")
data class ProductEntry(
    @SerializedName("admin_graphql_api_id")
    val adminGraphqlApiId: String,
    @SerializedName("body_html")
    val bodyHtml: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("handle")
    val handle: String,
    @PrimaryKey
    val id: Long,
    @Embedded(prefix = "image_")
    val image: ProductImage,
    @SerializedName("product_type")
    val productType: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("published_scope")
    val publishedScope: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("variants")
    val variants: List<Variant>,
    @SerializedName("vendor")
    val vendor: String
)