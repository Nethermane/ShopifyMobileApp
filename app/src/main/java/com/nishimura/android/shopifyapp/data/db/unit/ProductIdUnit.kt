package com.nishimura.android.shopifyapp.data.db.unit

import androidx.room.ColumnInfo

data class ProductIdUnit(
    @ColumnInfo(name="productId")
    val productId: Long
)