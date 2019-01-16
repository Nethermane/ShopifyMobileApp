package com.nishimura.android.shopifyapp.data.db.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nishimura.android.shopifyapp.data.db.entity.entry.CollectEntry
import com.nishimura.android.shopifyapp.data.db.unit.ProductIdUnit

@Dao
interface ProductIdDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(collectionEntry: CollectEntry)
    @Query("select * from product_query_result where collectionId = :collectionId")
    fun getProductIdsFromCollection(collectionId: Long): LiveData<List<ProductIdUnit>>
}
