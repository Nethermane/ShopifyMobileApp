package com.nishimura.android.shopifyapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import com.nishimura.android.shopifyapp.data.db.entity.dao.CollectionDao
import com.nishimura.android.shopifyapp.data.db.entity.dao.ProductDao
import com.nishimura.android.shopifyapp.data.db.entity.dao.ProductIdDao
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit
import com.nishimura.android.shopifyapp.data.db.unit.ProductIdUnit
import com.nishimura.android.shopifyapp.data.db.unit.ProductUnit
import com.nishimura.android.shopifyapp.data.network.CollectionDataSource
import com.nishimura.android.shopifyapp.data.network.response.CustomCollectionsResponse
import com.nishimura.android.shopifyapp.data.network.response.ProductIDsResponse
import com.nishimura.android.shopifyapp.data.network.response.ProductsResponse

class CollectionRepositoryImpl(
    private val collectionDao: CollectionDao,
    private val collectionDataSource: CollectionDataSource,
    private val productIdDao: ProductIdDao,
    private val productDao: ProductDao
) : CollectionRepository {

    init {
        //Observe forever because when this repository is destroyed the app is as well
        //This is not really necessary because I am choosing not to p
        collectionDataSource.downloadedCurrentCollection.observeForever { newCollection ->
            persistCollection(newCollection)
        }
        collectionDataSource.downloadedProductIDsResponse.observeForever { newProductId ->
            persistProductIds(newProductId)
        }
        collectionDataSource.downloadProducts.observeForever { newProduct ->
            persistProducts(newProduct)
        }
    }

    override suspend fun getCollections(): LiveData<List<CollectionUnit>> {
        return withContext(Dispatchers.IO) {
            initCollectionData()
            return@withContext collectionDao.getCollectionTitle()
        }
    }

    override suspend fun getProducts(collectionId: Long): LiveData<List<ProductUnit>> {
        return withContext(Dispatchers.IO) {
            initProducts(collectionId)
            return@withContext productDao.getProductsFromId(collectionId)

        }
    }

    private fun persistCollection(fetchedCollection: CustomCollectionsResponse) {
        //Global scope is viable here because this app will always be alive if this class is alive
        GlobalScope.launch(Dispatchers.IO) {
            for (collection in fetchedCollection.customCollectionEntries)
                collectionDao.upsert(collection)
        }
    }

    private fun persistProductIds(fetchedCollection: ProductIDsResponse) {
        //Global scope is viable here because this app will always be alive if this class is alive
        GlobalScope.launch(Dispatchers.IO) {
            for (collection in fetchedCollection.collects)
                productIdDao.upsert(collection)
        }
    }

    private fun persistProducts(fetchedCollection: ProductsResponse) {
        //Global scope is viable here because this app will always be alive if this class is alive
        GlobalScope.launch(Dispatchers.IO) {
            for (collection in fetchedCollection.products)
                productDao.upsert(collection)
        }
    }

    private suspend fun initCollectionData() {
        //Temp set always true
        if (isFetchedCurrentNeeded())
            fetchCurrentCollection()
    }


    private suspend fun initProducts(collectionId: Long) {
        //Temp set always true
        if (isFetchedCurrentNeeded())
            fetchCurrentProductFromId(collectionId)
    }

    private suspend fun fetchCurrentCollection() {
        //Gets data then updates it, observed in init
        collectionDataSource.fetchCurrentCollection()
    }
    private suspend fun fetchCurrentProductFromId(collectionId: Long) {
        //Gets data then updates it, observed in init
        collectionDataSource.fetchProductsFromCollectionId(collectionId)
    }

    private fun isFetchedCurrentNeeded(): Boolean {
        return true
    }
}