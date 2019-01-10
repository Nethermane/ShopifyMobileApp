package com.nishimura.android.shopifyapp.ui.collection

import androidx.lifecycle.ViewModel
import com.nishimura.android.shopifyapp.data.repository.CollectionRepository
import com.nishimura.android.shopifyapp.internal.lazyDeferred

class CollectionViewModel(
    private val collectionRepository: CollectionRepository
) : ViewModel() {
    val collection by lazyDeferred {
        collectionRepository.getCollections()
    }
}