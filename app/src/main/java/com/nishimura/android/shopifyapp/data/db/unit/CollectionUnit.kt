package com.nishimura.android.shopifyapp.data.db.unit

import androidx.room.ColumnInfo
import com.nishimura.android.shopifyapp.data.db.entity.Image
import java.io.Serializable

data class CollectionUnit(
    @ColumnInfo(name="title")
    val title: String,
    @ColumnInfo(name="image_src")
    val image_src: String,
    @ColumnInfo(name="bodyHtml")
    val bodyHtml: String,
    @ColumnInfo(name="id")
    val id: Long
): Serializable