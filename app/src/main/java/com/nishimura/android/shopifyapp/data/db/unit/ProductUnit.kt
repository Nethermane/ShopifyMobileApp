package com.nishimura.android.shopifyapp.data.db.unit

import androidx.room.ColumnInfo
import com.nishimura.android.shopifyapp.data.db.entity.ProductImage
import com.nishimura.android.shopifyapp.data.db.entity.Variant

data class ProductUnit(
    @ColumnInfo(name="variants")
    val variants: List<Variant>,
    @ColumnInfo(name="title")
    val title: String,
    @ColumnInfo(name = "image_src")
    val image_src: String
    )