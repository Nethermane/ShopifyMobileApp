package com.nishimura.android.shopifyapp.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit
import com.nishimura.android.shopifyapp.data.db.unit.ProductUnit
import com.nishimura.android.shopifyapp.data.repository.CollectionRepository
import com.nishimura.android.shopifyapp.internal.lazyDeferred
import kotlinx.coroutines.Deferred
import kotlin.properties.Delegates

class ProductViewModel(
    private val collectionRepository: CollectionRepository
) : ViewModel() {
    private var collectionId: Long by Delegates.notNull()
    private val collection by lazyDeferred {
        collectionRepository.getProducts(collectionId)
    }
    fun getCollection(collectionId: Long): Deferred<LiveData<List<ProductUnit>>> {
        this.collectionId = collectionId
        return collection
    }
    fun getCollection(collectionUnit: CollectionUnit): Deferred<LiveData<List<ProductUnit>>> {
        this.collectionId = collectionUnit.id
        return collection
    }
}