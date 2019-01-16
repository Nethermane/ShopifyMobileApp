package com.nishimura.android.shopifyapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nishimura.android.shopifyapp.data.network.response.CustomCollectionsResponse
import com.nishimura.android.shopifyapp.data.network.response.ProductIDsResponse
import com.nishimura.android.shopifyapp.data.network.response.ProductsResponse
import java.io.IOException

class CollectionDataSourceImpl(private val shopifyApiService: ShopifyApiService) :
    CollectionDataSource {
    private val _downloadedCurrentCollection = MutableLiveData<CustomCollectionsResponse>()
    override val downloadedCurrentCollection: LiveData<CustomCollectionsResponse>
        get() = _downloadedCurrentCollection
    private val _downloadedProductIDsResponse = MutableLiveData<ProductIDsResponse>()
    override val downloadedProductIDsResponse: LiveData<ProductIDsResponse>
        get() = _downloadedProductIDsResponse
    private val _downloadProducts = MutableLiveData<ProductsResponse>()
    override val downloadProducts: LiveData<ProductsResponse>
        get() = _downloadProducts
    override suspend fun fetchCurrentCollection() {
        try {
            val fetchCollection = shopifyApiService
                .getCollections()
                .await()
            _downloadedCurrentCollection.postValue(fetchCollection)
        }
        catch (e : IOException) {
            Log.e("CollectionDataSourceImp", "no internet connection", e)
        }
    }
    override suspend fun fetchProductsFromCollectionId() {
        try {
            val fetchCollection = shopifyApiService
                .getProductIDsFromCollection()
                .await()
            _downloadedProductIDsResponse.postValue(fetchCollection)
        }
        catch (e : IOException) {
            Log.e("CollectionDataSourceImp", "no internet connection", e)
        }
    }

    override suspend fun fetchProducts() {
        try {
            val fetchCollection = shopifyApiService
                .getProductsById()
                .await()
            _downloadProducts.postValue(fetchCollection)
        }
        catch (e : IOException) {
            Log.e("CollectionDataSourceImp", "no internet connection", e)
        }
    }

}