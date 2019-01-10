package com.nishimura.android.shopifyapp.data.db.entity

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("admin_graphql_api_id")
    val adminGraphqlApiId: String,
    @SerializedName("body_html")
    val bodyHtml: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("handle")
    val handle: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("productImage")
    val productImage: ProductImage,
    @SerializedName("productImages")
    val productImages: List<ProductImage>,
    @SerializedName("options")
    val options: List<Option>,
    @SerializedName("product_type")
    val productType: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("published_scope")
    val publishedScope: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("template_suffix")
    val templateSuffix: Any,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("variants")
    val variants: List<Variant>,
    @SerializedName("vendor")
    val vendor: String
)