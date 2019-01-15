package com.nishimura.android.shopifyapp.data.network

import androidx.lifecycle.LiveData
import com.nishimura.android.shopifyapp.data.network.response.CustomCollectionsResponse
import com.nishimura.android.shopifyapp.data.network.response.ProductIDsResponse

interface CollectionDataSource {
    val downloadedCurrentCollection: LiveData<CustomCollectionsResponse>
    val downloadedProductIDsResponse: LiveData<ProductIDsResponse>
    suspend fun fetchCurrentCollection()
    suspend fun fetchProductsFromCollectionId()
}