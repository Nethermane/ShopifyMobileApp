package com.nishimura.android.shopifyapp.data.repository

import androidx.lifecycle.LiveData
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit
import com.nishimura.android.shopifyapp.data.db.unit.ProductIdUnit

interface CollectionRepository {
    suspend  fun getCollections() : LiveData<List<CollectionUnit>>
    suspend fun getProductIDsFromCollectionID(collectionId: String = "") : LiveData<List<ProductIdUnit>>
}