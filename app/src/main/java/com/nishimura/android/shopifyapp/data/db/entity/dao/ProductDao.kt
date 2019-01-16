package com.nishimura.android.shopifyapp.data.db.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nishimura.android.shopifyapp.data.db.entity.entry.ProductEntry
import com.nishimura.android.shopifyapp.data.db.unit.ProductUnit

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(collectionEntry: ProductEntry)
    @Query("select * from products where id in (select productId from product_query_result where collectionId = :collectionId)")
    fun getProductsFromId(collectionId: Long): LiveData<List<ProductUnit>>
}
