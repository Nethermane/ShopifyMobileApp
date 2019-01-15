package com.nishimura.android.shopifyapp.data.db.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit
import com.nishimura.android.shopifyapp.data.db.unit.ProductIdUnit

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(collectionEntry: CollectEntry)
    @Query("select * from product_query_result where collectionId = :collectionId")
    fun getProductIdsFromCollection(collectionId: String = ""): LiveData<List<ProductIdUnit>>
}
