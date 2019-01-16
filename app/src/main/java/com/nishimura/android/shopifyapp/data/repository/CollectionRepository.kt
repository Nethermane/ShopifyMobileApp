package com.nishimura.android.shopifyapp.data.repository

import androidx.lifecycle.LiveData
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit
import com.nishimura.android.shopifyapp.data.db.unit.ProductIdUnit
import com.nishimura.android.shopifyapp.data.db.unit.ProductUnit

interface CollectionRepository {
    suspend  fun getCollections() : LiveData<List<CollectionUnit>>
    suspend fun getProducts(collectionId: Long) : LiveData<List<ProductUnit>>
    //suspend fun getProductIDsFromCollectionID(collectionId: Long): LiveData<List<ProductIdUnit>>
}