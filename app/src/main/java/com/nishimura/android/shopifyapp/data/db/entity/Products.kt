package com.nishimura.android.shopifyapp.data.db.entity

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("products")
    val products: List<Product>
)