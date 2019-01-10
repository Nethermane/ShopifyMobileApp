package com.nishimura.android.shopifyapp.data

class ShopifyDatabase private constructor() {
    companion object {
        @Volatile private var instance: ShopifyDatabase? = null

        fun getInstance() = instance?: synchronized(this) {
            instance?: ShopifyDatabase().also{instance = it}
        }
    }
}