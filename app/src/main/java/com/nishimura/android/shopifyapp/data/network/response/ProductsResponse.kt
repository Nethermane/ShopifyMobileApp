package com.nishimura.android.shopifyapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.nishimura.android.shopifyapp.data.db.entity.ProductEntry

data class ProductsResponse(
    @SerializedName("products")
    val products: List<ProductEntry>
)