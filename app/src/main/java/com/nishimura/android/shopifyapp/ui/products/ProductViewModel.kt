package com.nishimura.android.shopifyapp.ui.products

import androidx.lifecycle.ViewModel
import com.nishimura.android.shopifyapp.data.repository.CollectionRepository
import com.nishimura.android.shopifyapp.internal.lazyDeferred

class ProductViewModel(
    private val collectionRepository: CollectionRepository
) : ViewModel() {
    var collectionId: Long? = null
    val collection by lazyDeferred {
        collectionRepository.getProducts(collectionId)

    }
}