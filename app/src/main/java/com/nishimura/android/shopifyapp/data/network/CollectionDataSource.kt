package com.nishimura.android.shopifyapp.data.network

import androidx.lifecycle.LiveData
import com.nishimura.android.shopifyapp.data.network.response.CustomCollectionsResponse
import com.nishimura.android.shopifyapp.data.network.response.ProductIDsResponse
import com.nishimura.android.shopifyapp.data.network.response.ProductsResponse

interface CollectionDataSource {
    val downloadedCurrentCollection: LiveData<CustomCollectionsResponse>
    val downloadedProductIDsResponse: LiveData<ProductIDsResponse>
    val downloadProducts: LiveData<ProductsResponse>
    suspend fun fetchCurrentCollection()
    suspend fun fetchProductsFromCollectionId()
    suspend fun fetchProducts()
}