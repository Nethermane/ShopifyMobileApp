package com.nishimura.android.shopifyapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nishimura.android.shopifyapp.data.network.response.CustomCollectionsResponse
import com.nishimura.android.shopifyapp.data.network.response.ProductIDsResponse
import java.io.IOException

class CollectionDataSourceImpl(private val shopifyApiService: ShopifyApiService) :
    CollectionDataSource {
    private val _downloadedCurrentCollection = MutableLiveData<CustomCollectionsResponse>()
    override val downloadedCurrentCollection: LiveData<CustomCollectionsResponse>
        get() = _downloadedCurrentCollection
    private val _downloadedProductIDsResponse = MutableLiveData<ProductIDsResponse>()
    override val downloadedProductIDsResponse: LiveData<ProductIDsResponse>
        get() = _downloadedProductIDsResponse
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
            Log.e("WATTT", _downloadedProductIDsResponse.value.toString())
        }
        catch (e : IOException) {
            Log.e("CollectionDataSourceImp", "no internet connection", e)
        }
    }

}