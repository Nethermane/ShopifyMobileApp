package com.nishimura.android.shopifyapp.utilities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nishimura.android.shopifyapp.data.db.entity.Variant


class DataConverter {
    companion object {
        @JvmStatic
        @TypeConverter
        fun fromVariantList(variantList: List<Variant>?): String? {
            if (variantList == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<Variant>>() {

            }.type
            return gson.toJson(variantList, type)
        }

        @JvmStatic
        @TypeConverter
        fun toVariantList(variantString: String?): List<Variant>? {
            if (variantString == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<Variant>>() {

            }.type
            return gson.fromJson(variantString, type)
        }
    }
}